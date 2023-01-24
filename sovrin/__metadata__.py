"""
sovrin package metadata
"""

import os
import json

METADATA_FILENAME = 'metadata.json'
METADATA_FILE = os.path.join(
    os.path.abspath(os.path.dirname(__file__)), METADATA_FILENAME)


def loadAuthor(metadata_file: str = METADATA_FILE):
    with open(metadata_file, 'r') as f:
        data = json.load(f)
        return data['author']


def loadLicense(metadata_file: str = METADATA_FILE):
    with open(metadata_file, 'r') as f:
        data = json.load(f)
        return data['license']


def loadVersion(metadata_file: str = METADATA_FILE):
    with open(metadata_file, 'r') as f:
        data = json.load(f)
        return data['version']


__author__ = loadAuthor()
__license__ = loadLicense()
__version__ = loadVersion()


__all__ = ['__version__', '__author__', '__license__']
