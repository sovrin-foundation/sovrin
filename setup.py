#!/usr/bin/env python

import sys
import os

from setuptools import setup, find_packages, __version__


try:
    SETUP_DIRNAME = os.path.abspath(os.path.dirname(__file__))
except NameError:
    # We're probably being frozen, and __file__ triggered this NameError
    # Work around this
    SETUP_DIRNAME = os.path.abspath(os.path.dirname(sys.argv[0]))

# if SETUP_DIRNAME != '':
#     os.chdir(SETUP_DIRNAME)

# SETUP_DIRNAME = os.path.abspath(SETUP_DIRNAME)

METADATA = {'__file__': os.path.join(SETUP_DIRNAME, 'sovrin', '__metadata__.py')}

with open(METADATA['__file__'], 'r') as f:
    exec(f.read(), METADATA)

setup(
    name='sovrin',
    version=METADATA['__version__'],
    description='Sovrin node',
    url='https://github.com/sovrin-foundation/sovrin',
    author=METADATA['__author__'],
    author_email='support@sovrin.org',
    license=METADATA['__license__'],
    keywords='Sovrin Genesis Transactions',
    packages=find_packages(exclude=['docs', 'docs*']),
    package_data={
        '': ['*.txt', '*.md', '*.rst', '*.json', '*.conf', '*.html',
             '*.css', '*.ico', '*.png', 'LICENSE', 'LEGAL', '*.indy']},
    include_package_data=True,

    install_requires=['indy-node==1.13.2', 'sovtoken==1.1.1', 'sovtokenfees==1.1.1'],
)