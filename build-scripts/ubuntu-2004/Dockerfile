FROM ubuntu:20.04

RUN apt-get update -y && apt-get install -y \
    # common stuff
    git \
    wget \
    unzip \
    python3-pip \
    python-setuptools \
    python3-venv \
    # fpm
    ruby \
    ruby-dev \
    rubygems \
    gcc \
    make

# install fpm
RUN gem install --no-document rake fpm

WORKDIR /root

ADD . /root
