FROM gitpod/workspace-full as base

USER gitpod

RUN sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 3BC8C2DD662F1C45 && \
    sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 9692C00E657DDE61 && \
    sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys CE7709D068DB5E88 && \
    sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 9692C00E657DDE61

RUN sudo add-apt-repository "deb https://sovrin.jfrog.io/artifactory/deb focal dev"
RUN sudo add-apt-repository "deb https://hyperledger.jfrog.io/artifactory/indy focal dev rc"

RUN sudo apt-get update

RUN sudo apt-get install -y \
    python3-dev \
    indy-plenum=1.13.1~rc2 \
    python3-libnacl=1.6.1 \
    python3-sortedcontainers=1.5.7 \
    python3-ujson=1.33

RUN sudo apt install sovtoken sovtokenfees indy-node=1.13.0~dev1654534721 -y
RUN sudo -H pip install /sovtoken /sovtokenfees