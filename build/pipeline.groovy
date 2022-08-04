#!groovy

Map _delegateConfig(
        Map config,
        Closure body,
        List reqParams=[],
        Closure preCheck={},
        String logPrefix='') {

    def _logPrefix = "delegateConfig" + (logPrefix ? "-$logPrefix" : '')

    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    preCheck(config, reqParams)

    echo "$_logPrefix: parameters: $config"

    def err = []

    reqParams.each {
        if (!config[it]) {
            err.add("$it is not defined")
        }
    }

    if (err) {
        err = err.join('\n')
        error "$_logPrefix: $err"
    }

    return config
}

def _nodeWrapper(label=null, body) {
    echo "Running on `${label}` node type"
    node(label) {
        try {
            body()
        } finally {
            echo "Cleanup"
            cleanWs()
        }
    }
}

def _shStdout(script) {
    sh(returnStdout: true, script: script).trim()
}

String getDebianCandidate(String pkgName) {
    return _shStdout("""
        apt-cache policy $pkgName | grep Candidate | awk '{print \$2}'
    """)
}


String getPinnedDebianDependencyVersion(String pkgName, String depPkgName, String pkgVersion=null) {
    String deps

    if (!pkgVersion) {
        deps = _shStdout("""
            dpkg -s $pkgName | grep "^Depends"
        """)
    } else {
        deps = _shStdout("""
            apt-cache show $pkgName=$pkgVersion | grep "^Depends"
        """)
    }

    return _shStdout("""
        echo '$deps' | sed 's/.*${depPkgName}\\( \\)\\?(=\\([^)]\\+\\)).*/\\2/g'
    """)
}


String getInstalledDebianVersion(String pkgName) {
    return _shStdout("""
        dpkg -s "$pkgName" | grep "^Version" | cut -d ' ' -f 2
    """)
}


String pkgManifestData(String pkgName) {
    String manifestPath = _shStdout("""
        dpkg -L $pkgName | grep manifest
    """)

    if (!manifestPath) {
        error "Manifest for $pkgName is not found"
    }

    return _shStdout("cat $manifestPath")
}

String resolveServerEnv(String repoChannel, String sovrinVersion=null) {
    Map res = [
        sovrin: [:],
        sovtokenfees: [:],
        sovtoken: [:],
        indyNode: [:],
        indyPlenum: [:],
        libindyCrypto: [:]
    ]

    docker.image('hyperledger/indy-core-baseci:0.0.3-master').inside('-u 0') {
        // TODO current release logic of sovtoken omits `rc` when final release happens,
        // thus 'rc' for the sovrin has to deal with both rc and stable components
        String repoComponents = (repoChannel == 'rc' ? 'rc stable' : repoChannel)
        sh """
            sed -i 's/repo.sovrin.org\\/deb xenial master/repo.sovrin.org\\/deb xenial $repoComponents/g' /etc/apt/sources.list
            cat /etc/apt/sources.list
            apt-get update
        """

        res.sovrin.ver = sovrinVersion ?: getDebianCandidate('sovrin')

        // sovtoken is not pinned in master for now
        if (repoChannel == 'master') {
            res.sovtoken.ver = getDebianCandidate('sovtoken')
        } else {
            res.sovtoken.ver = getPinnedDebianDependencyVersion(
                'sovrin', 'sovtoken', res.sovrin.ver
            )
        }

        // FIXME: for now it's not possible to resolve src version of plugins from debian packages
        res.sovtoken.src = (repoChannel == 'rc' ? 'stable' : repoChannel)

        res.sovtokenfees.ver = res.sovtoken.ver
        res.indyNode.ver = getPinnedDebianDependencyVersion(
            'sovtoken', 'indy-node', res.sovtoken.ver
        )
        res.indyPlenum.ver = getPinnedDebianDependencyVersion(
            'indy-node', 'indy-plenum', res.indyNode.ver
        )
        res.libindyCrypto.ver = getPinnedDebianDependencyVersion(
            'indy-plenum', 'python3-indy-crypto', res.indyPlenum.ver
        )

        sh """
            apt-get install -y \
                sovrin=${res.sovrin.ver} \
                sovtoken=${res.sovtoken.ver} \
                sovtokenfees=${res.sovtokenfees.ver} \
                indy-node=${res.indyNode.ver} \
                indy-plenum=${res.indyPlenum.ver} \
                python3-indy-crypto=${res.libindyCrypto.ver}
        """

        res.sovrin.manifest = pkgManifestData('sovrin').tokenize('\n')[1].tokenize()
        // TODO for these ones manifest is json
        res.indyNode.manifest = pkgManifestData('indy-node')
        res.indyPlenum.manifest = pkgManifestData('indy-plenum')
    }

    return res
}

String resolveClientEnv(
    String sovtokenSrcVersion,
    String sovtokenRepoUrl='https://github.com/sovrin-foundation/token-plugin.git'
) {
    Map res = [
        libindy: [:],
        libsovtoken: [:],
    ]
    String sovtokenScmDir = '_plugins'
    String ciDockerPath = 'devops/docker/ci/xenial/Dockerfile'

    Closure parseSovtokenCIDependency = { String depPkgName ->
        return _shStdout("""
            grep "${depPkgName}=" $ciDockerPath | sed 's/.*${depPkgName}=\\(.*\\) .*/\\1/g'
        """)
    }

    checkout([
        $class: 'GitSCM',
        branches: [[name: sovtokenSrcVersion]],
        userRemoteConfigs: [[
            url: sovtokenRepoUrl,
        ]],
        extensions: [[
            $class: 'RelativeTargetDirectory',
            relativeTargetDir: sovtokenScmDir
        ]]
    ])

    dir(sovtokenScmDir) {
        res.libindy.ver = parseSovtokenCIDependency('libindy')
        res.libsovtoken.ver = parseSovtokenCIDependency('libsovtoken')
        res.libindy.pypi = res.libindy.ver.replaceAll(/~(.*)/, "-dev-\$1")
    }

    return res
}

def systemTests(Closure body) {
    String prefix = "System Tests"
    String systemTestsNetwork = 'indy-test-automation-network'
    String systemTestsDir = './system_tests'

    def config = _delegateConfig([
            repoChannel: 'master',
            clientRepoChannel: null,
            sovrinVer: null,
            sovtokenfeesVer: null,
            sovtokenVer: null,
            indyNodeVer: null,
            indyPlenumVer: null,
            libindyCryptoVer: null,
            libsovtokenVer: null,
            libindyVer: null,
            libindyPypiVer: null,
            sovtokenRepoUrl: 'https://github.com/sovrin-foundation/token-plugin.git',
            testSchema: [['.']],
            testVersion: null,
            testVersionByTag: false,
            gatherLogs: true
        ],
        body, [
            'sovrinVer',
            'sovtokenfeesVer',
            'sovtokenVer',
            'indyNodeVer',
            'indyPlenumVer',
            'libindyCryptoVer',
            'libsovtokenVer',
            'libindyVer',
            'libindyPypiVer',
        ], { it, _ ->
            if (!it.clientRepoChannel) {
                it.clientRepoChannel = (it.libindyVer == it.libindyPypiVer) ? 'stable' : 'master'
            }
        }, prefix
    )

    Map systemTestsParams = [
        targetDir: systemTestsDir
    ]

    if (config.testVersion) {
        if (!!config.testVersionByTag) {
            systemTestsParams.tag = config.testVersion
        } else {
            systemTestsParams.branch = config.testVersion
        }
    }

    def dockerClean = {
        sh "./system/docker/clean.sh $systemTestsNetwork"

        try {
            sh "docker ps -q --filter network=$systemTestsNetwork | xargs -r docker rm -f"
        } catch (Exception exc) {
            echo "$prefix: failed to remove docker containers in $systemTestsNetwork network: $exc"
            throw exc
        }

        try {
            sh "docker network ls -q --filter name=$systemTestsNetwork | xargs -r docker network rm"
        } catch (Exception exc) {
            echo "$prefix: failed to remove docker $systemTestsNetwork network: $exc"
            throw exc
        }

        sh "docker container prune -f"
        sh "docker network prune -f"
    }

    def runTest = { String testGroup ->

        stage("[${testGroup}] Checkout system tests") {
            testHelpers.getSystemTests(systemTestsParams)
        }

        dir(systemTestsDir) {
            stage("[${testGroup}] Patch system tests python requirements") {
                sh """
                    sed -i 's/python3-indy.*/python3-indy==${config.libindyPypiVer}/g' ./system/requirements.txt
                """
            }

            stage("[${testGroup}] Cleanup docker") {
                dockerClean()
            }

            stage("[${testGroup}] Prepare docker env") {
                // TODO see the comment above
                String repoComponents = (config.repoChannel == 'rc' ? 'rc stable' : config.repoChannel)
                List envVars = [
                    "INDY_NODE_REPO_COMPONENT=${repoComponents}",
                    "LIBINDY_CRYPTO_VERSION=${config.libindyCryptoVer}",
                    "PYTHON3_LIBINDY_CRYPTO_VERSION=${config.libindyCryptoVer}",
                    "INDY_PLENUM_VERSION=${config.indyPlenumVer}",
                    "INDY_NODE_VERSION=${config.indyNodeVer}",
                    "TOKEN_PLUGINS_INSTALL=yes",
                    "SOVRIN_VERSION=${config.sovrinVer}",
                    "SOVTOKEN_VERSION=${config.sovtokenVer}",
                    "SOVTOKENFEES_VERSION=${config.sovtokenfeesVer}",
                    "LIBINDY_REPO_COMPONENT=${config.clientRepoChannel}",
                    "LIBINDY_VERSION=${config.libindyVer}",
                    "LIBSOVTOKEN_INSTALL=yes",
                    "LIBSOVTOKEN_VERSION=${config.libsovtokenVer}",
                ]

                echo "[${testGroup}]: env variables: $envVars"
                withEnv(envVars) {
                    sh "./system/docker/prepare.sh $systemTestsNetwork"
                }
            }

            try {
                def err
                String testReportFileNameXml = "system_tests_${testGroup}_report.${config.repoChannel}.xml"
                String testReportFileNamePlain = "system_tests_${testGroup}_report.${config.repoChannel}.txt"
                String testTargets = config.testSchema[testGroup].collect{"system/indy-node-tests/$it"}.join(' ')
                String buildLogsDir = "_build/logs"
                String gatherLogsOpt = config.gatherLogs ? ' --gatherlogs' : ''

                try {
                    stage("[${testGroup}] Run tests") {
                        sh """
                            bash -c "\
                                set -o pipefail; \
                                ./system/docker/run.sh \
                                    \\"$testTargets\\" \
                                    \\"--payments -l -vv --junit-xml=$testReportFileNameXml ${gatherLogsOpt} --logsdir=${buildLogsDir}\\" \
                                    \\"$systemTestsNetwork\\" 2>&1 | tee $testReportFileNamePlain;\
                            "
                        """
                    }
                } catch (_err) {
                    err = _err
                    throw _err
                } finally {
                    stage("[${testGroup}] Upload test report") {
                        sh "ls -la *report* || true"
                        if (err) {
                            archiveArtifacts artifacts: testReportFileNamePlain, allowEmptyArchive: true
                            archiveArtifacts artifacts: "$buildLogsDir/**/*", allowEmptyArchive: true
                        }
                        junit testResults: testReportFileNameXml, allowEmptyResults: true
                    }
                }
            } catch (Exception exc) {
                echo "$prefix: fail: $exc"
                throw exc
            } finally {
                stage("[${testGroup}] Cleanup docker") {
                    dockerClean()
                }
            }
        }
    }

    Map builds = [:]
    for (int i = 0; i < config.testSchema.size(); i++) {
        String testNames = config.testSchema[i].join(' ')
        Boolean isFirst = (i == 0)
        int testGroup = i
        builds[testNames] = {
            stage("Run ${testNames}") {
                _nodeWrapper('ubuntu') {
                    runTest(testGroup)
                }
            }
        }
    }
    builds.failFast = false

    parallel builds
}

return this;
