#!/usr/bin/env bash

set -x
set -e

INPUT_PATH=$1
OUTPUT_PATH=${2:-.}

PACKAGE_NAME=sovrin

fpm --input-type "python" \
    --output-type "deb" \
    --architecture "amd64" \
    --verbose \
    --python-package-name-prefix "python3" \
    --python-bin "/usr/bin/python3" \
    --exclude "*.pyc" \
    --exclude "*.pyo" \
    --maintainer "Sovrin Foundation <repo@sovrin.org>" \
    --before-install "preinst" \
    --after-install "postinst" \
    --before-remove "prerm" \
    --no-python-fix-dependencies \
    --name ${PACKAGE_NAME} \
    --package ${OUTPUT_PATH} \
    ${INPUT_PATH}
