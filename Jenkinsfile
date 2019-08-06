#!groovy

@Library('SovrinHelpers@v1.2.1') _

def name = 'sovrin'

def buildDebUbuntu = { repoName, releaseVersion, sourcePath ->
    def volumeName = "sovrin-deb-u1604"
    if (env.BRANCH_NAME != '' && env.BRANCH_NAME != 'master') {
        volumeName = "${volumeName}.${BRANCH_NAME}"
    }
    if (sh(script: "docker volume ls -q | grep -q '^$volumeName\$'", returnStatus: true) == 0) {
        sh "docker volume rm $volumeName"
    }
    dir('build-scripts/ubuntu-1604') {
        sh "./build-sovrin-docker.sh \"$sourcePath\" $releaseVersion $volumeName"
    }
    return "$volumeName"
}

def systemTests = { component, releaseVersion ->
    def localLib
    Map serverEnv = [:]
    Map clientEnv = [:]
    String clientRepoChannel = null

    String emailRecipients = params.INDY_NODE_RECIPIENTS ?: env.INDY_NODE_RECIPIENTS ?: ''
    Boolean gatherLogs = env.GATHER_LOGS != 'false'

    node('ubuntu') {
        try {
            stage('Load local shared library') {
                checkout scm
                localLib = load 'build/pipeline.groovy'
            }

            stage("Get server env") {
                serverEnv = localLib.resolveServerEnv(component, releaseVersion)
                echo "Server env: $serverEnv"
            }

            stage("Get client env") {
                clientEnv = localLib.resolveClientEnv(serverEnv.sovtoken.src)
                echo "Client env: $clientEnv"

                clientRepoChannel = (clientEnv.libindy.ver == clientEnv.libindy.src) ? 'stable' : 'master'
            }
        } finally {
            cleanWs()
        }
    }

    localLib.systemTests {
        delegate.repoChannel = component
        sovrinVer = serverEnv.sovrin.ver
        sovtokenfeesVer = serverEnv.sovtokenfees.ver
        sovtokenVer = serverEnv.sovtoken.ver
        indyNodeVer = serverEnv.indyNode.ver
        indyPlenumVer = serverEnv.indyPlenum.ver
        libindyCryptoVer = serverEnv.libindyCrypto.ver
        libsovtokenVer = clientEnv.libsovtoken.ver
        libindyVer = clientEnv.libindy.ver
        libindyPypiVer = clientEnv.libindy.pypi
        testSchema = [
            ['test_ledger.py'],
            ['test_vc.py'],
            ['test_consensus.py', 'TestTAASuite.py'],
            ['test_upgrade.py', 'test_roles.py', 'test_freshness.py', 'TestMultiSigSuite.py'],
            ['TestAuditSuite.py'],
            // TODO might be groupped in parts once https://github.com/docker/docker-py/issues/2278 is resolved
            ['TestAuthMapAttribSuite.py'],
            ['TestAuthMapCredDefSuite.py'],
            ['TestAuthMapMiscSuite.py'],
            ['TestAuthMapNymSuite.py'],
            ['TestAuthMapPluginsSuite.py'],
            ['TestAuthMapRevocRegDefSuite.py'],
            ['TestAuthMapRevocRegEntrySuite.py'],
            ['TestAuthMapSchemaSuite.py'],
            ['TestAuthMapUpgradeSuite.py'],
            ['test_libsovtoken.py', 'TestFeesSuite.py'],
        ]
        testVersion = 'v0.8.10'
        testVersionByTag = true
        delegate.gatherLogs = gatherLogs
    }
}


options = new TestAndPublishOptions()
options.enable([StagesEnum.PACK_RELEASE_COPY, StagesEnum.PACK_RELEASE_COPY_ST])
options.setCopyWithDeps(false)

if (env.BRANCH_NAME == 'stable') {
    options.setSystemTestsCb(systemTests)
}

testAndPublish(name, [ubuntu: [:]], true, options, [ubuntu: buildDebUbuntu])
