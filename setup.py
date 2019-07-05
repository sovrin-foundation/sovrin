#!/usr/bin/env python

import sys
import os

from setuptools import setup, find_packages, __version__


try:
    SETUP_DIRNAME = os.path.dirname(__file__)
except NameError:
    # We're probably being frozen, and __file__ triggered this NameError
    # Work around this
    SETUP_DIRNAME = os.path.dirname(sys.argv[0])

if SETUP_DIRNAME != '':
    os.chdir(SETUP_DIRNAME)

SETUP_DIRNAME = os.path.abspath(SETUP_DIRNAME)

METADATA = os.path.join(SETUP_DIRNAME, 'sovrin', '__metadata__.py')
# Load the metadata using exec()
# so we don't trigger an import of ioflo.__init__
exec(compile(open(METADATA).read(), METADATA, 'exec'))

setup(
    name='sovrin',
    version=__version__,
    description='Sovrin node',
    url='https://github.com/sovrin-foundation/sovrin',
    author=__author__,
    author_email='dev@evernym.us',
    license=__license__,
    keywords='Sovrin Genesis Transactions',
    packages=find_packages(exclude=['docs', 'docs*']),
    package_data={
        '': ['*.txt', '*.md', '*.rst', '*.json', '*.conf', '*.html',
             '*.css', '*.ico', '*.png', 'LICENSE', 'LEGAL', '*.indy']},
    include_package_data=True,

    install_requires=['indy-node==1.9.0', 'sovtoken==1.0.0', 'sovtokenfees==1.0.0'],
)
