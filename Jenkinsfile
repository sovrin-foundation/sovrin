#!groovy

@Library('SovrinHelpersNewPackageNames') _

def name = 'sovrin'

options = new TestAndPublishOptions()
options.enable([StagesEnum.PACK_RELEASE_DEPS, StagesEnum.PACK_RELEASE_ST_DEPS])
testAndPublish(name, [ubuntu: [:]], true, options)
