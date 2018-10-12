#!/bin/bash -xe

abspath() {
    perl -e 'use Cwd "abs_path"; print abs_path(shift)' $1
}

PKG_SOURCE_PATH="$1"
VERSION="$2"
PKG_NAME=sovrin
IMAGE_NAME="${PKG_NAME}-build-u1604"
OUTPUT_FOLDER="$3"


if [ -z "${PKG_SOURCE_PATH}" ] ; then
    PKG_SOURCE_PATH=$(abspath ${PWD}/../..)
fi

if [ -z "${VERSION}" ] ; then
    VERSION=$(egrep '\d[\.\d]*' "${PKG_SOURCE_PATH}/release-notes.md"  | head -n 1 | cut -d '[' -f 2 | cut -d ']' -f 1)
fi

if [[ (-z "${PKG_SOURCE_PATH}") || (-z "${VERSION}") ]]; then
    echo "Usage: $0 <path-to-package-sources> <version> <volume>"
    exit 1;
fi

if [ -z "${OUTPUT_FOLDER}" ] ; then
    OUTPUT_FOLDER=$(abspath ${PWD}/${PKG_NAME}-deb-u1604)
fi

if [ -d "${OUTPUT_FOLDER}" ] ; then
    rm -rf "${OUTPUT_FOLDER}"
fi
mkdir -p "${OUTPUT_FOLDER}"

if [ -z "$4" ]; then
    CMD="/root/build-${PKG_NAME}.sh /input ${VERSION} /output"
else
    CMD="$4"
fi

docker build -t "${IMAGE_NAME}" -f Dockerfile .
DOCKER_IMAGE_ID=$(docker image ls | grep ${IMAGE_NAME})

if [ -z "${DOCKER_IMAGE_ID}" ] ; then
    docker build -t "${IMAGE_NAME}" -f Dockerfile .
fi

echo "PKG_SOURCE_PATH=${PKG_SOURCE_PATH}"
echo "OUTPUT_FOLDER=${OUTPUT_FOLDER}"
echo "PKG_NAME=${PKG_NAME}"
echo "CMD=${CMD}"

docker run \
    --rm \
    -v "${PKG_SOURCE_PATH}:/input" \
    -v "${OUTPUT_FOLDER}:/output" \
    -e PKG_NAME="${PKG_NAME}" \
    "${IMAGE_NAME}" \
    $CMD

