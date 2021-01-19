# Sovrin and Indy-Node acceptance testing

## Automated tests

Automated tests cover ~95% of Indy-Node functionality so it is the main step of acceptance testing. They are here: `https://github.com/hyperledger/indy-test-automation/tree/master/system` and they are integrated to Indy-Node and Sovrin CI/CD pipelines (but they also can be run locally with the same steps as described in Jenkinsfiles in both repositories) so both pipelines must be green to move artifacts from RC to stable.

## Semi-automated tests

Semi-automated tests cover various upgrade cases - they require manual input of initial artifacts' versions in Dockerfiles (`https://github.com/hyperledger/indy-test-automation/tree/master/system/docker`) and final artifacts versions in tests (basic case: `https://github.com/hyperledger/indy-test-automation/blob/master/system/draft/test_upgrade_docker_7.py` and all other `test_upgrade_docker_7_*.py` cases).

## Manual tests

Since all automated tests use docker we must check:
- final artifacts installation on real machine with Ubuntu 16 (local or AWS)
- upgrade of pool of 25 nodes (local or AWS) that was initialized from the first Sovrin release and then was upgraded sequentially to each Sovrin release one by one (so it behaves like real production environment and keeps state between many upgrades)
