# Release Workflow

## Branches

- `master` is a development branch.
- `stable` is a release branch.

## Continuous Delivery Pipeline

- CD pipeline is defined in [Jenkinsfile](../Jenkinsfile) file.
- CD pipeline is run on Sovrin Foundation Jenkins [server](https://build.sovrin.org/job/sovrin/job/sovrin-cd/) dealing with issuing and uploading new builds. It includes:
    - debian packages creation and publishing;
    - system testing that verifies that published artifacts are installable and installed pool passes tests on system level;
    - manual approval procedure that requires QA participation to allow final steps of the release workflow.

## Release Workflow

`sovrin` package is the top of the software stack that includes [token-plugins](https://github.com/sovrin-foundation/token-plugin)
and [indy-node](https://github.com/hyperledger/indy-node). These dependency packages use different release workflows and `sovrin`
has to deal with that. The following workflow describes the current logic which likely will be changed / optimized in future.

1. Release candidate preparation
    1. `indy-node`:
        - `indy-plenum` stable release happens;
        - `indy-node` release candidate is prepared;
        - please check [this docs](https://github.com/hyperledger/indy-node/blob/master/docs/source/ci-cd.md#release-workflow) for more details.
    2. `token-plugins`:
        - release candidate is prepared as a package that linked to `indy-node` release candidate version.
    3. `sovrin`:
        - release candidate is prepared as a package that linked to release candidates of `token-plugins` and `indy-node`.
2. Release candidate acceptance
    1. CD pipeline performs system testing
    2. Once the tests pass QA validate the release:
        - if QA denies the pipeline is stopped and the workflow repeats from the beginning to update and publish new release candidates of dependency packages;
        - if QA approves the pipeline **is stopped** as well but the workflow continues.
3. Stable release preparation
    1. `indy-node`:
        - release candidate is accepted and published to stable release channels.
    2. `token-plugin`:
        - new release packages that are linked to **stable** `indy-node` version are created and published to stable release channels.
    3. `sovrin`:
        - new release candidate is prepared as a package that is linked to stable versions of `token-plugins` and `indy-node`.
4. Stable release acceptance
    1. The acceptance process is the same as for release candidate. The only difference is that in case of approval QA
       allows pipeline to proceed and publish the stable package to stable release channel.
