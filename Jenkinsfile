#!groovy

@Library('SovrinHelpersNewPackageNames') _

def name = 'sovrin'

def options = new TestAndPublishOptions()
options.setPublishableBranches(['feature/indy-399']) //REMOVE IT BEFORE MERGE     
options.setPostfixes([master: 'new-names']) //REMOVE IT BEFORE MERGE
options.skip([StagesEnum.GITHUB_RELEASE])
testAndPublish(name, [ubuntu:[:]], true, options)
