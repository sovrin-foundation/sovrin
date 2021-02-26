### Github Actions Workflow

`sovrin-build` workflow (`build.yml`) replaces the existing `Jenkinsfile` build process.

The build process for `Jenkins.nightly` was not ported to GHA.

#### Configuring actions

If you are cloning or forking this repo you will need to configure two secrets for Actions to run correctly.

Secrets can be set via Settings -> Secrets -> New repository secret.

CR_USER is your GH username.
CR_PAT can be created by following [these directions](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token)

Once you have run the build once with those secrets, you have to make then package public.
Access the package at https://ghcr.io/USER/sovrin/indy-node-lint then change the visibility in 'Package Settings' to 'Public' then re-run the build.
