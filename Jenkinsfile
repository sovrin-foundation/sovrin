#!groovy

@Library('SovrinHelpers') _

def name = 'sovrin'

def buildDebUbuntu = { repoName, releaseVersion, sourcePath ->
    def volumeName = "sovrin-deb-u1604"
    sh "docker volume rm -f $volumeName"
    dir('build-scripts/ubuntu-1604') {
        sh "./build-sovrin-docker.sh $sourcePath $releaseVersion"
    }
    return "$volumeName"
}

options = new TestAndPublishOptions()
options.enable([StagesEnum.PACK_RELEASE_COPY, StagesEnum.PACK_RELEASE_COPY_ST])
options.setCopyWithDeps(true)
testAndPublish(name, [ubuntu: [:]], true, options, [ubuntu: buildDebUbuntu])
