# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.245.2/containers/ubuntu/.devcontainer/base.Dockerfile

# [Choice] Ubuntu version (use ubuntu-22.04 or ubuntu-18.04 on local arm64/Apple Silicon): ubuntu-22.04, ubuntu-20.04, ubuntu-18.04
ARG VARIANT="jammy"
FROM mcr.microsoft.com/vscode/devcontainers/base:0-${VARIANT}

# [Optional] Uncomment this section to install additional OS packages.
# RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
#     && apt-get -y install --no-install-recommends <your-package-list-here>

RUN apt-get update && apt-get install -y software-properties-common

RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 3BC8C2DD662F1C45 && \
    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 9692C00E657DDE61 && \
    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys CE7709D068DB5E88 && \
    apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 9692C00E657DDE61

RUN add-apt-repository "deb https://sovrin.jfrog.io/artifactory/deb focal dev"
RUN add-apt-repository "deb https://hyperledger.jfrog.io/artifactory/indy focal dev rc"

RUN apt-get update

RUN apt-get install -y \
    python3-dev \
    python3-pip \
    indy-plenum=1.13.1~rc2 \
    python3-libnacl=1.6.1 \
    python3-sortedcontainers=1.5.7 \
    python3-ujson=1.33 \
    rubygems

RUN gem install fpm
RUN apt-get install sovtoken sovtokenfees indy-node=1.13.2~rc3 -y
RUN pip install /sovtoken /sovtokenfees
RUN apt remove indy-node indy-plenum -y