# Release Workflow

## Branches

- `master` is a development branch.
- `stable` is a release branch.

## Continuous Delivery Pipeline

- CD pipeline is defined in [Jenkinsfile](../Jenkinsfile) file.
- CD pipeline is [run](https://build.sovrin.org/job/sovrin/job/sovrin-cd/) by the Sovrin Foundation Jenkins server dealing with issuing and uploading new builds. It includes:
    - debian packages creation and publishing;
    - system testing that verifies that published artifacts are installable and installed pool passes tests on system level;
    - manual approval procedure that requires QA participation to allow final steps of the release workflow.

## Release Workflow

`sovrin` package is the top of the software stack that includes [token plugins](https://github.com/sovrin-foundation/token-plugin)
and [indy-node](https://github.com/hyperledger/indy-node). These dependency packages use different release workflows and `sovrin`
has to deal with that. The following list of steps describes the current logic which likely will be changed and/or optimized in future.

1. Release Candidate Preparation
    1. `indy-node`:
        - `indy-plenum` stable release happens.
        - `indy-node` release candidate is prepared.
        - Please check [this docs](https://github.com/hyperledger/indy-node/blob/master/docs/source/ci-cd.md#release-workflow) for more details.
    2. `token plugins`:
        - Release candidate is prepared as a package that linked to `indy-node` release candidate version.
        - Please check [this docs](https://github.com/sovrin-foundation/token-plugin/blob/master/docs/release.md) for more details.
    3. `sovrin`:
        1. [**Contributor**]
            - Create a branch from `stable`.
            - Apply necessary changes from `master` (either `merge` or `cherry-pick`).
            - Set `indy-node`, `sovtoken` and `sovtokenfees` release candidates versions in [setup.py](../setup.py).
            - Create a pull request to `stable`.
        2. [**build server**]
            - Run CI for the PR.
        3. [**Maintainer**]
            - Review, approve and merge the PR.
        4. [**build server**] Run release pipeline:
            - Evaluate a release version as `X.Y.B`, where `X.Y` are parsed from [sovrin/\_\_metadata__.py](../sovrin/__metadata__.py)  and `B` is a Jenkins build number.
            - Publish to PyPI.
            - Build and publish a debian package to `rc-latest` channel of the debian repository.
            - Copy the package from `rc-latest` to `rc` channel.
            - Run system tests for the `rc` channel.
            - Send notifications.
            - Push a tag `X.Y.B-rc` to GitHub.
            - Wait for an approval to proceed.
2. Release Candidate Acceptance
    1. `indy-node`:
        - See [docs](https://github.com/hyperledger/indy-node/blob/master/docs/source/ci-cd.md#release-workflow).
    2. `token plugins`:
        - See [docs](https://github.com/sovrin-foundation/token-plugin/blob/master/docs/release.md). **Note**: no dedicated QA acceptance is performed for plugins for now.
    3. `sovrin`:
        1. [**QA**]
            - Perform acceptance testing for the full software stack (`sovrin`, `token plugins`, `indy-node`, `indy-plenum`).
            - If the candidate is denied stop the pipeline and the workflow repeats from the beginning to update and publish new release candidates for the project and (_optionally_) its dependencies (`indy-node`, `indy-plenum`, `token plugins`).
            - Otherwise the pipeline **is stopped** as well but the workflow continues.
3. Stable Release Preparation and Acceptance
    1. `indy-node`:
        - Release candidate is accepted and published to `stable` release channels (debian, PyPI).
        - See [docs](https://github.com/hyperledger/indy-node/blob/master/docs/source/ci-cd.md#release-workflow).
    2. `token-plugin`:
        - new release packages linked to **stable** `indy-node` version are created and published to `stable` release channel.
        - See [docs](https://github.com/sovrin-foundation/token-plugin/blob/master/docs/release.md).
    3. `sovrin`:
        - New release candidate is prepared as a package `X.Y.B+1` linked to stable versions of `token plugins` and `indy-node`.
        - The process is the same as described above with the following difference:
            - [**QA**] In case of approval allow pipeline to proceed and publish the stable package to stable release channel.
            - [**build server**]
                - Once QA approves continues the pipeline.
                - Copy the debian package from `rc-latest` to `stable-latest` channel.
                - Copy the package from `stable-latest` to `stable` channel.
                - Run system tests for the `stable` channel.
                - Push a tag `X.Y.B+1` to GitHub.
                - Notify maintainers.
