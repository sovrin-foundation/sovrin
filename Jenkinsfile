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
options.enable([StagesEnum.PACK_RELEASE_DEPS, StagesEnum.PACK_RELEASE_ST_DEPS])
options.skip([StagesEnum.TEST, StagesEnum.PYPI_RELEASE])
options.setPublishableBranches(['bugfix/indy-425']) //REMOVE IT BEFORE MERGE
options.setPostfixes([master: 'hold-test']) //REMOVE IT BEFORE MERGE
testAndPublish(name, [ubuntu: [:]], true, options, [ubuntu: buildDebUbuntu])
