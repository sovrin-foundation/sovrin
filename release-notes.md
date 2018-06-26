# Sovrin Release Notes





![image alt text](banner.png)



* [1.1.11](#1111)

* [1.1.10](#1110)

* [1.1.9](#119)

* [1.1.8](#118)

* [1.1.7](#117)

* [1.1.6 Hot Fix](#116-hot-fix)

* [1.1.6](#116)

* [1.0](#10)






#### Disclosure

Although every attempt has been made to make this information as accurate as possible, please know there may be things that are omitted, not fully developed yet, or updates since this publication that were not included in the information below. Only the most pressing or significant items have been listed. For the entire list of tickets and or specific information about any given item, please visit the list at [Hyperleder Indy's Jira](https://jira.hyperledger.org/). Once logged in, simply navigate to Projects > Indy.


## 1.1.11


### Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-plenum | 1.4.43 |
| indy-anoncreds | 1.0.11 |
| indy-node | 1.4.63 |
| release version number | 1.1.11 |
|   |   |   |

### Major Fixes

| Description | Additional Information | Ticket Number |
| --- | --- | --- |
| Fixed and issues where one of the nodes stopped writing after 44287 txns with errors in status. |   | [INDY-1410](https://jira.hyperledger.org/browse/INDY-1410) |
| Fixed an issue where the pool stopped accepting transactions on 5731 txns (1 sec delays, no logging). |   | [INDY-1365](https://jira.hyperledger.org/browse/INDY-1365) |
| Fixed an issue where the pool stopped writing after ~300,000 txns from 5 clients |   | [INDY-1315](https://jira.hyperledger.org/browse/INDY-1315) |
| Fixed an issue where STN was not accepting transactions with only one node down. |   | [INDY-1351](https://jira.hyperledger.org/browse/INDY-1351) |
| Fixed an issue where the pool stops taking txns at ~178k txns written in ledger. |   | [INDY-1260](https://jira.hyperledger.org/browse/INDY-1260) |
| Fixed an issue where `ReqIdrToTxn` does not store information about the ledger. |   | [INDY-1327](https://jira.hyperledger.org/browse/INDY-1327) |
| Made simple Timeout fixes of the current View Change protocol. |   | [INDY-1341](https://jira.hyperledger.org/browse/INDY-1341) |
| Fixed an issue where the migration fails in case of upgrade to version with new transactions format. |   | [INDY-1379](https://jira.hyperledger.org/browse/INDY-1379) |
| Fixed an issue where `--network parameter of read_ledger ` doesn't work. |   | [INDY-1318](https://jira.hyperledger.org/browse/INDY-1318) |
| Fixed an issue where the /var/log/indy/validator-info.log was inappropriately owned by root. |   | [INDY-1310](https://jira.hyperledger.org/browse/INDY-1310) |
| Created a fix around the issues found in the current logic of catch-up. |   | [INDY-1298](https://jira.hyperledger.org/browse/INDY-1298) |
| Fixed GetValidatorInfo so it has correct validation for signature and permissions. |   | [INDY-1363](https://jira.hyperledger.org/browse/INDY-1363) |
| Fixed an issue where there was an unhandled exception during node working. |   | [INDY-1316](https://jira.hyperledger.org/browse/INDY-1316) |
| Fixed an issue where `validator-info` and `read_ledger` were giving inconsistent responses in node on provisional. |   | [INDY-1219](https://jira.hyperledger.org/browse/INDY-1219) |
| Fixed an issue where the pool stops taking txns at 3000 writing connections. |   | [INDY-1259](https://jira.hyperledger.org/browse/INDY-1259) |
|   |   |   |
|   |   |   |   |

### Changes - Additions - Known Issues

| Description | Workaround | Ticket |
| --- | --- | --- |
| Reviewed and replaced `assert` with exceptions in indy-plenum where needed. |   | [INDY-810](https://jira.hyperledger.org/browse/INDY-810) |
| Tuned RocksDB options for the best performance. |   | [INDY-1245](https://jira.hyperledger.org/browse/INDY-1245) |
| Created a migration guide from Indy-node 1.3 to 1.4. |   | [INDY-1392](https://jira.hyperledger.org/browse/INDY-1392) |
| Сhanged a key in the requests map and field reqIdr in Pre Prepare and Ordered. |   | [INDY-1370](https://jira.hyperledger.org/browse/INDY-1370) |
| Investigated issues found during load testing of 25-nodes pool with increased timeouts for catchups and viewchange. |   | [INDY-1400](https://jira.hyperledger.org/browse/INDY-1400) |
| We now support binding on separate NICs for Client-to-Node and Node-to-Node communication. |   | [INDY-1332](https://jira.hyperledger.org/browse/INDY-1332) |
| Added short checkpoints stabilization without matching digests. |   | [INDY-1329](https://jira.hyperledger.org/browse/INDY-1329) |
| Added indy-crypto package to the hold list. |   | [INDY-1323](https://jira.hyperledger.org/browse/INDY-1323) |
| Removed ledger status based catch-up trigger together with the wrong catch-up workflow. |   | [INDY-1297](https://jira.hyperledger.org/browse/INDY-1297) |
| Read-ledger without storage copy in case of RocksDB (RocksDB read-only mode support). |   | [INDY-1243](https://jira.hyperledger.org/browse/INDY-1243) |
| Applied state machine to Catchup code. |   | [INDY-971](https://jira.hyperledger.org/browse/INDY-971) |
| Refactored the common Request structure. |   | [INDY-1124](https://jira.hyperledger.org/browse/INDY-1124) |
| Refactored the common transactions structure. |   | [INDY-1123](https://jira.hyperledger.org/browse/INDY-1123) |
| We now support the new libindy with changed txn format. |   | [INDY-1319](https://jira.hyperledger.org/browse/INDY-1319) |
| Explored config parameters to find the best performance/stability settings. |   | [INDY-1334](https://jira.hyperledger.org/browse/INDY-1334) |
| Extended the Validator Info tool to provide more information about the current state of the pool. |   | [INDY-1175](https://jira.hyperledger.org/browse/INDY-1175) |
|  A Steward needs to be able to get validator-info from all nodes. |   | [INDY-1184](https://jira.hyperledger.org/browse/INDY-1184) |
| Modified existing load scripts for a better load testing. |   | [INDY-1279](https://jira.hyperledger.org/browse/INDY-1279) |
| Performed a migration from LevelDB to RocksDB |   | [INDY-1244](https://jira.hyperledger.org/browse/INDY-1244) |
| A Trustee needs to be able to restart the pool in critical situations. |   | [INDY-1173](https://jira.hyperledger.org/browse/INDY-1173) |
| Move the log compression into separate process. |   | [INDY-1275](https://jira.hyperledger.org/browse/INDY-1275) |
| **Known Issue:** There's an incorrect read\_ledger info with seq\_no parameter. |   | [INDY-1415](https://jira.hyperledger.org/browse/INDY-1415) |
| **Known Issue:** Pool upgrade should be performed simultaneously for all nodes due to txn format changes. All indy-cli pools should be recreated with actual genesis files. |   |   |
| **List of breaking changes for migration from indy-node 1.3 to 1.4:** |   | [1.3-1.4 Migration Guide](https://github.com/hyperledger/indy-node/blob/master/docs/1.3_to_1.4_migration_guide.md) |

### Upgrade Scripts:

**Pool upgrade should be performed simultaneously for all nodes due to txn format changes.**

**All indy-cli pools should be recreated with actual genesis files.**

#### CLI Upgrading:

**Old CLI (`indy`):**

upgrade from 1.3 to 1.4 version
delete `~.ind-cli/networks/&lt;network_name&gt;/data` folder
replace both old genesis files by new ones (from 1.4 node or from sovrin repo)

**New CLI (`indy-cli`):**

upgrade from 1.4 to 1.5 version
recreate indy-cli pool using 1.4 pool genesis file (from 1.4 node or from sovrin repo)

### Additional Information:

**List of breaking changes for migration from indy-node 1.3 to 1.4:**

https://github.com/hyperledger/indy-node/blob/master/docs/1.3\_to\_1.4\_migration\_guide.md

**IndyNode 1.4 and LibIndy 1.5 compatibility:**

_General_

By default LibIndy 1.5 will be compatible with IndyNode 1.3 (current stable), and not 1.4 (the new one).

LibIndy 1.5 can become compatible with IndyNode 1.4 if `indy_set_protocol_version(2)` is called during app initialization.

_Guideline for teams and apps_

Applications can freely update to LibIndy 1.5 and still use stable Node 1.3

If an app wants to work with the latest master or Stable Node 1.4, then they need to support breaking changes (there are not so many, mostly a new reply for write txns as txn format is changed, see 1.3\_to\_1.4\_migration\_guide.md)

call `indy_set_protocol_version(2)` during app initialization


## 1.1.10


### Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-plenum | 1.2.42 |
| indy-anoncreds | 1.0.11 |
| indy-node | 1.3.62 |
| release version number | 1.1.10 |
|   |   |    |

### Major Fixes

| Description | Additional Information | Ticket Number |
| --- | --- | --- |
| Fixed an issue where the STN was losing consensus. |   | [INDY-1256](https://jira.hyperledger.org/browse/INDY-1256) |
| Fixed an issue where we were unable to use the read\_ledger tool with the parameter "to". |   | [INDY-1284](https://jira.hyperledger.org/browse/INDY-1284) |
|Fixed the upgrade from 1.2.223 (1.3.55 stable analogue) to 1.3.410 (rocksdb) wasn't working.|    |[INDY-1330](https://jira.hyperledger.org/browse/INDY-1330)  |
|   |   |   |    |   

### Changes - Additions - Known Issues

| Description | Workaround | Ticket |
| --- | --- | --- |
| Support was added for supervisord. |   | [https://github.com/hyperledger/indy-node/pull/588](https://jira.hyperledger.org/browse/INDY-1186) |
| Indy-node dependencies are fixed.  |   |    |
|   |   |   |    |

### Upgrade Scripts:

None for this release.

### Additional Information:

None at this time.


## 1.1.9

### Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-node | 1.3.56 |
| release version number | 1.1.9 |
|   |   |   |

### Major Fixes

| Description | Additional Information | Ticket Number |
| --- | --- | --- |
| The Node was restarting because of an &quot;Out of memory&quot; error. |   | [INDY-1238](https://jira.hyperledger.org/browse/INDY-1238) |
| The pool was not working after not simultaneous manual pool upgrades. |   | [INDY-1197](https://jira.hyperledger.org/browse/INDY-1197) |
| When adding a new schema, field &#39;attr\_names&#39; of schema json can be an empty list. |   | [INDY-1169](https://jira.hyperledger.org/browse/INDY-1169) |
| This prevents an Identity Owner from creating a schema or claimDef. |   | [INDY-1111](https://jira.hyperledger.org/browse/INDY-1111) |
| There was the same primary for both instances 0 and 1. |   | [INDY-1112](https://jira.hyperledger.org/browse/INDY-1112) |
| The node logs were being duplicated in syslog. |   | [INDY-1102](https://jira.hyperledger.org/browse/INDY-1102) |
| It was possible to create several nodes with the same alias. |   | [INDY-1148](https://jira.hyperledger.org/browse/INDY-1148) |
| There was ambiguous behavior after node demotion. |   | [INDY-1179](https://jira.hyperledger.org/browse/INDY-1179) |
| One of the nodes were not responding to libindy after several running load tests. |   | [INDY-1180](https://jira.hyperledger.org/browse/INDY-1180) |
| When returning N-F nodes to the pool, &quot;View change&quot; was not occurring if the Primary node was stopped. |   | [INDY-1151](https://jira.hyperledger.org/browse/INDY-1151) |
| There was a failed restart after getting the &quot;unhandled exception (KeyError)&quot;. |   | [INDY-1152](https://jira.hyperledger.org/browse/INDY-1152) |
| Fixed a bug where you were unable to install indy-node if sdk repo is in sources.list   |   |[INDY-1269](https://jira.hyperledger.org/browse/INDY-1269)   |  
|   |   |   |   |

### Changes - Additions - Known Issues

| Description | Workaround | Ticket |
| --- | --- | --- |
| Made it so that a developer can distinguish logs of each replica. |   | [INDY-1186](https://jira.hyperledger.org/browse/INDY-1186) |
| Made it so a developer, can track the path of each request. |   | [INDY-1187](https://jira.hyperledger.org/browse/INDY-1187) |
| Made it so that you can use RocksDB as a key-value storage. |   | [INDY-1205](https://jira.hyperledger.org/browse/INDY-1205) |
| Refactored the common Request structure. |   | [INDY-1124](https://jira.hyperledger.org/browse/INDY-1124) |
| Made it so that it supports anoncreds revocation in Indy. |   | [INDY-680](https://jira.hyperledger.org/browse/INDY-680) |
| Made it so that it supports REVOC\_REG\_DEF transaction. |   | [INDY-1134](https://jira.hyperledger.org/browse/INDY-1134) |
| Made it so that it supports GET\_REVOC\_REG\_DEF request. |   | [INDY-1135](https://jira.hyperledger.org/browse/INDY-1135) |
| Made it so that it supports REVOC\_REG\_ENTRY transaction. |   | [INDY-1136](https://jira.hyperledger.org/browse/INDY-1136) |
| Made it so that it supports GET\_REVOC\_REG request. |   | [INDY-1137](https://jira.hyperledger.org/browse/INDY-1137) |
| Made it so that it supports getting state root by timestamp. |   | [INDY-1138](https://jira.hyperledger.org/browse/INDY-1138) |
| Got rid of the RAET code. |   | [INDY-1057](https://jira.hyperledger.org/browse/INDY-1057) |
| Incubation: Move CI part of pipelines to Hyperledger infrastructure. |   | [INDY-837](https://jira.hyperledger.org/browse/INDY-837) |
| Made it so that a user can revoke a connection by rotating the new key to nothing. |   | [INDY-582](https://jira.hyperledger.org/browse/INDY-582) |
| **Known Issue:** Define the policy how to restore node from the state when it&#39;s stashing all the reqs and there is a risk of running out of memory. |   | [INDY-1250](https://jira.hyperledger.org/browse/INDY-1250) |
| **Known Issue:** Re-promoted node cannot hook up to a lower viewChange. |   | [INDY-1199](https://jira.hyperledger.org/browse/INDY-1199) |
| **Known Issue:** One of the nodes does not respond to libindy after several running load test. |   | [INDY-1180](https://jira.hyperledger.org/browse/INDY-1180) |
| **Known Issue:** One node fails behind others during the load\_test with a high load. |   | [INDY-1188](https://jira.hyperledger.org/browse/INDY-1188) |
|**Known Issue:** Pool can be broken by primary node reboot in case of network issues between nodes. **Note:** RocksDB was added as dependency (INDY-1205). It is used for revocation, but the rest part of node functionality is still using LevelDB.   |   |[INDY-1256](https://jira.hyperledger.org/browse/INDY-1256)       |
|   |   |   |    |

### Upgrade Scripts

None for this release.

### Additional Information:

None at this time.


## 1.1.8

**Important: Upgrade to this version should be performed simultaneously for all nodes (with `force=True`).**

### Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-plenum | 1.2.34 |
| indy-anoncreds | 1.0.11 |
| indy-node | 1.3.55 |
| release version number | 1.1.8 |
|   |   |    |

### Major Fixes

| Description | Additional Information | Ticket Number |
| --- | --- | --- |
| Transactions were missing from the config ledger after the upgrade. |   | [INDY-799](https://jira.hyperledger.org/browse/INDY-799) |
| The node was broken after a load\_test.py run. |   | [INDY-960](https://jira.hyperledger.org/browse/INDY-960) |
| The pool stopped taking transactions after sending 1,000 simultaneous transactions. |   | [INDY-911](https://jira.hyperledger.org/browse/INDY-911) |
| The pool stopped working: Node services stop with 1,000 simultaneous clients doing GET\_NYM reads |   | [INDY-986](https://jira.hyperledger.org/browse/INDY-986) |
| The node is broken after adding it to the pool. |   | [INDY-948](https://jira.hyperledger.org/browse/INDY-948) |
| The generate\_indy\_pool\_transactions command can be run only by an indy user. |   | [INDY-1048](https://jira.hyperledger.org/browse/INDY-1048) |
| Made it so that updates to existing Schemas are not allowed. |   | [INDY-1035](https://jira.hyperledger.org/browse/INDY-1035) |
| The pool was unable to write txns after two nodes adding. |   | [INDY-1018](https://jira.hyperledger.org/browse/INDY-1018) |
| Fixed a bug where it was possible to override CLAIM\_DEF for existing schema-did pair. |   | [INDY-1083](https://jira.hyperledger.org/browse/INDY-1083) |
| Fixed a bug where here was a huge amount of calls and a lot of execution time in kv\_store.py. |   | [INDY-1077](https://jira.hyperledger.org/browse/INDY-1077) |
| One of added nodes wasn&#39;t catching up. |   | [INDY-1029](https://jira.hyperledger.org/browse/INDY-1029) |
| The pool stopped working and lost consensus while new node was performing a catch-up. |   | [INDY-1025](https://jira.hyperledger.org/browse/INDY-1025) |
| Performing a View Change on large pools of 19 or more nodes can cause pool to stop functioning. |   | [INDY-1054](https://jira.hyperledger.org/browse/INDY-1054) |
| Performing a View Change issue stopped the pool from accepting new transactions. |   | [INDY-1034](https://jira.hyperledger.org/browse/INDY-1034) |
| We were unable to send transactions in STN. |   | [INDY-1076](https://jira.hyperledger.org/browse/INDY-1076) [INDY-1079](https://jira.hyperledger.org/browse/INDY-1079) |
| Replica.lastPrePrepareSeqNo may not be reset on view change. |   | [INDY-1061](https://jira.hyperledger.org/browse/INDY-1061) |
| We were unable to send an upgrade transaction without including demoted nodes. |   | [INDY-897](https://jira.hyperledger.org/browse/INDY-897) |
| The Nym request to STN was resulting in inconsistent responses. |   | [INDY-1069](https://jira.hyperledger.org/browse/INDY-1069) |
| The validator node was being re-promoted during view change. |   | [INDY-959](https://jira.hyperledger.org/browse/INDY-959) |
| There was a false cancel message during an upgrade. |   | [INDY-1078](https://jira.hyperledger.org/browse/INDY-1078) |
| Transactions were being added to nodes in STN during system reboot.. |   | [INDY-1045](https://jira.hyperledger.org/browse/INDY-1045) |
| There were problems with nodes demotion during load test. |   | [INDY-1033](https://jira.hyperledger.org/browse/INDY-1033) |
| The node monitoring tool (email plugin) wasn&#39;t working. |   | [INDY-995](https://jira.hyperledger.org/browse/INDY-995) |
| ATTRIB transaction with ENC and HASH wasn&#39;t working. |   | [INDY-1074](https://jira.hyperledger.org/browse/INDY-1074) |
| When returning N-F nodes to the pool, View Change does not occur if Primary node is stopped. |   | [INDY-1151](https://jira.hyperledger.org/browse/INDY-1151) |
| We were unable to recover write consensus at n-f after f+1 descent. |   | [INDY-1166](https://jira.hyperledger.org/browse/INDY-1166) |
| Newly upgraded STN fails to accept transactions (pool has been broken after upgrade because of one not upgraded node).  |   |[INDY-1183](https://jira.hyperledger.org/browse/INDY-1183)   |   
|We were unable to submit upgrade transactions to STN.   |    |[INDY-1190](https://jira.hyperledger.org/browse/INDY-1190)     
|   |    |    |    |

### Changes - Additions - Known Issues

| Description | Workaround | Ticket |
| --- | --- | --- |
| Added indy-sdk test dependency to plenum and use indy-sdk for plenum tests. |   | [INDY-900](https://jira.hyperledger.org/browse/INDY-900) [INDY-901](https://jira.hyperledger.org/browse/INDY-901) |
| Published docker images to dockerhub. |   | [INDY-962](https://jira.hyperledger.org/browse/INDY-962) |
| Simplified the view change code. |   | [INDY-480](https://jira.hyperledger.org/browse/INDY-480) |
| Refactored config.py to reflect file folder re-factoring for Incubation. |   | [INDY-878](https://jira.hyperledger.org/browse/INDY-878) |
| Added Abstract Observers Support. |   | [INDY-628](https://jira.hyperledger.org/browse/INDY-628) |
| Moved scripts from sovrin-environment to one of Indy repos. |   | [INDY-1055](https://jira.hyperledger.org/browse/INDY-1055) |
| Got rid of Sovrin dependency in the environment scripts. |   | [INDY-1064](https://jira.hyperledger.org/browse/INDY-1064) |
| Updated information in &quot;Getting Started with Indy&quot;. |   | [INDY-1062](https://jira.hyperledger.org/browse/INDY-1062) |
| Updated information in &quot;Setting Up a Test Indy Network in VMs&quot;. |   | [INDY-1062](https://jira.hyperledger.org/browse/INDY-1062) |
| Add iptables rules to limit the number of clients connections. |   | [INDY-1087](https://jira.hyperledger.org/browse/INDY-1087) |
| Knowledge transfer on Indy build processes. |   | [INDY-1088](https://jira.hyperledger.org/browse/INDY-1088) |
| Incubation: Move CI part of pipelines to Hyperledger infrastructure. |   | [INDY-837](https://jira.hyperledger.org/browse/INDY-837) |
| Made it so that a user can revoke a connection by rotating the new key to nothing. |   | [INDY-582](https://jira.hyperledger.org/browse/INDY-582) |
| Client needs to be able to make sure that we have the latest State Proof. |   | [INDY-928](https://jira.hyperledger.org/browse/INDY-928) |
| Created it so that anyone could have access to an up-to-date Technical overview of plenum and indy. |   | [INDY-1022](https://jira.hyperledger.org/browse/INDY-928) |
| **Known Issue:** Pool has lost consensus after primary demotion (with 4 nodes setup only). |   | [INDY-1163](https://jira.hyperledger.org/browse/INDY-1163) |
| **Known Issue:** Ambiguous behavior after node demotion. |   | [INDY-1179](https://jira.hyperledger.org/browse/INDY-1179) |
| **Known Issue:** One of the nodes does not respond to libindy after several running load test. |   | [INDY-1180](https://jira.hyperledger.org/browse/INDY-1180) |
|**Known Issue:** Pool does not work after not simultaneous manual pool upgrades.   |   |[INDY-1197](https://jira.hyperledger.org/browse/INDY-1197)   |   
|**Known Issue:** Pool stops working if the primary node was not included to schedule in the upgrade transaction.   |   |[INDY-1198](https://jira.hyperledger.org/browse/INDY-1198)  |
|   |   |     |     |


### Additional Information:

Node promoting is not recommended for 1.3.52 version according to known issues because backup protocol instances may work incorrectly until next view change.

As mentioned above, upgrade to this version should be performed simultaneously for all nodes (with `force=True`).

## 1.1.7


### Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-plenum | 1.2.29 |
| indy-anoncreds | 1.0.11 |
| indy-node | 1.2.50 |
| release version number | 1.1.7 |
|   |   |   |

### Major Fixes

| Description | Additional Information | Ticket Number |
| --- | --- | --- |
| A node was maintaining a pace with the network exactly 12 transactions behind. |   | [INDY-759](https://jira.hyperledger.org/browse/INDY-759) |
| New nodes added to an existing pool were unable to sync ledgers with the pool. |   | [INDY-895](https://jira.hyperledger.org/browse/INDY-895) |
| Scheduled upgrades were happening at the current time on some of the nodes. |   | [INDY-231](https://jira.hyperledger.org/browse/INDY-231) |
| Some nodes were not restarting after a canceled pool upgrade. |   | [INDY-157](https://jira.hyperledger.org/browse/INDY-157) |
| Sovrin logs were insufficient for failed upgrade. |   | [INDY-801](https://jira.hyperledger.org/browse/INDY-801) |
| A node was getting the wrong `upgrade_log` entries after restarting and was running the wrong upgrade. |   | [INDY-917](https://jira.hyperledger.org/browse/INDY-917) |
| An earlier `pool_upgrade` was not happening when there was an upgrade to schedule to happen in the future. |   | [INDY-701](https://jira.hyperledger.org/browse/INDY-701) |
| A validator was running instance change continually on the live pool. |   | [INDY-932](https://jira.hyperledger.org/browse/INDY-932) |
| New nodes added to an existing pool were unable to participate in consensus after the upgrade. |   | [INDY-909](https://jira.hyperledger.org/browse/INDY-909) |
| The node logs were repeating the message, &quot;NodeRequestSuspiciousSpike suspicious spike has been noticed.&quot; |   | [INDY-541](https://jira.hyperledger.org/browse/INDY-541) |
| Unable to catch up the agent if a validator was down. |   | [INDY-941](https://jira.hyperledger.org/browse/INDY-941) |
| The pool was unable to write nyms after BLS keys enabling. |   | [INDY-958](https://jira.hyperledger.org/browse/INDY-958) |
| The last pool node is `failed to upgrade`; during a pool upgrade. |   | [INDY-953](https://jira.hyperledger.org/browse/INDY-953) |
| State Proof creating is fixed. |   | [INDY-954](https://jira.hyperledger.org/browse/INDY-954) |
| State Proof verifying is fixed. |   | [INDY-949](https://jira.hyperledger.org/browse/INDY-949) |
|   |   |   |    |

### Changes - Additions - Known Issues

| Description | Workaround | Ticket |
| --- | --- | --- |
| Signed State implementation |   | [INDY-670](https://jira.hyperledger.org/browse/INDY-670) |
| State Proofs implementation |   | [INDY-790](https://jira.hyperledger.org/browse/INDY-790) |
| Removed all non-Indy branding from the indy-plenum repo. |   | [INDY-829](https://jira.hyperledger.org/browse/INDY-829) |
| Removed all non-Indy branding from the indy-anoncreds repo. |   | [INDY-855](https://jira.hyperledger.org/browse/INDY-855) |
| Removed all non-Indy branding from the indy-node repo. |   | [INDY-830](https://jira.hyperledger.org/browse/INDY-830) |
| Backward compatibility of nodes with state proofs support with old clients. |   | [INDY-877](https://jira.hyperledger.org/browse/INDY-877) |
| Supported rebranding in sovrin package. |   | [INDY-880](https://jira.hyperledger.org/browse/INDY-880) |
| Supported rebranding in Docker and Vagrant environments of sovrin-environments. |   | [INDY-891](https://jira.hyperledger.org/browse/INDY-891) |
| Support of multiple pool networks by Indy Node. |   | [INDY-831](https://jira.hyperledger.org/browse/INDY-831) |
| Support of multiple pool networks by Indy Client (CLI). |   | [INDY-832](https://jira.hyperledger.org/browse/INDY-832) |
| Created proper file folder paths for system service. |   | [INDY-833](https://jira.hyperledger.org/browse/INDY-833) |
| Client needs to be able to send read requests to one Node only. |   | [INDY-927](https://jira.hyperledger.org/browse/INDY-927) |
| Client needs to be able to make sure that we have the latest State Proof. |   | [INDY-928](https://jira.hyperledger.org/browse/INDY-928) |
| **Known Issue:** Node is broken after `load_test.py` run |   | [INDY-960](https://jira.hyperledger.org/browse/INDY-960) |
|    |    |    |    |

### Additional Information:

Mapping of all file/folder changes are located [here](https://docs.google.com/spreadsheets/d/1A84H8knCtn8rrTirzxta8XC1jpHBjvQiqrxquTv6bpc/edit#gid=0).

#### Upgrade Steps


1. Send Pool Upgrade command so all nodes upgrade.

2. Sometime later each Steward will need to do the following steps to add their BLS Keys:

##### Steps to Add BLS Keys

**_From the Validator Node:_**

1. Generate a new 32-byte seed for the bls key (we recommend pwgen):

``$ sudo apt install pwgen
$ pwgen -s -y -B 32 1``

If the output has a single-quote symbol ('), rerun until it doesn't.

**NOTE: This is not your Steward or Node seed.**

2. Record the seed **somewhere secure**.

3. Switch to the indy user.

``$ sudo su - indy``

4. Configure the BLS key.

``$ init_bls_keys --name <NODE_ALIAS> --seed'<SEED>'``

The ``--seed`` is the seed you generated above, and will be used to create the BLS key.

_Example with Seed:_

 ``$ init_bls_keys --name Node1 --seed'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'``

Capture the stdout at the end of the output, which looks like the following, and record it.

`BLS Public key is 3AfkzUZVn2WT9mxW2zQXMgX39FXSY5qzohnMVpdvNS5KSath1YG5Ux4u9ubTFTaP6W55XX9Yx7xPWeYos489oyY53WzwNBG7X4o32ESnZ9xacLmNsQLBjqc6oqpWGTbEXv4edFTrZ88n93sEh4fjFhQMumaXxDfWJgd9aj7KCSpf38F`

5. Exit the indy user.

``$ exit ``

**_From the CLI Node:_**

1. Manually upgrade the CLI.

 ``$ sudo apt update``

 ``$ sudo apt upgrade``

2. Launch the CLI.

``$ indy ``

The first time running the upgraded CLI you will be prompted to migrate your previous settings. Answer "Yes."

3. Connect to the pool.

`indy> connect live`

4. Set your Steward as the signer in the CLI.

`indy@live> use DID <Steward DID>`

_Example:_

`indy@live> use DID Th7MpTaRZVRYnPiabds81Y`

**Note:** If your DID is not found in the wallet, you will need to use your steward seed:

`indy@live> new key with seed <steward_seed>`

5. Now you will send a node transaction like what you did when you added the node to the pool. You will add the BLS key as a new parameter to the transaction to update the pool ledger with this additional public key. For 'dest', use the same base58 value for this that was used when you initially onboarded your VM onto the provisional pool.

`indy@live> send NODE dest=<node_dest> data={'alias':'<node name>','blskey': '<key_generated_by_init_bls_keys>'}`

_Example:_

`indy@live< send NODE dest=Gw6pDLhcBcoQesN72qfotTgFa7cbuqZpkX3Xo6pLhPhv data={'alias':'Node1','blskey': '3AfkzUZVn2WT9mxW2zQXMgX39FXSY5qzohnMVpdvNS5KSath1YG5Ux4u9ubTFTaP6W55XX9Yx7xPWeYos489oyY53WzwNBG7X4o32ESnZ9xacLmNsQLBjqc6oqpWGTbEXv4edFTrZ88n93sEh4fjFhQMumaXxDfWJgd9aj7KCSpf38F'}`


#### Questions and Answers

##### BLS Keys for State Proofs


**What does BLS stand for?**

Boneh-Lynn-Shacham - The BLS signature scheme is used to verify that a signer is authentic.

**How does the CLI use State Proof for confirmation?**

When the CLI requests information about a transaction it checks the BLS signatures to verify the transaction was written by nodes that are part of the validator pool. The CLI sends a request to one node (arbitrary one). If the Reply doesn't have a State Proof, or the reply is incorrect/invalid, then CLI falls back to sending requests to all Nodes and waiting for f+1 equal Replies.

**What if not all nodes in the pool have BLS signing keys for a transaction?**

Transactions only get signed if all nodes reaching consensus can sign it (>= n-f Nodes with correct BLS signatures).

**Can the bls_seed be any 32 character seed like the Steward seed?**

Yes.

**When adding a new node to an existing pool where do I find my BLS key?**

When initializing your node using `init_indy_node` the output will display the keys for the node including the BLS key. It can be found in /var/lib/indy/<network_name>/keys/<node_name>/bls_keys/bls_pk file (e.g.: /var/lib/indy/sandbox/keys/Node1/bls_keys/bls_pk)

When you send the transaction to add the new node to the pool it will also contain the BLS key in the transaction shown in this example.

*Example of send node command with BLS for 5th node in test pool:*

 ``send NODE dest=4Tn3wZMNCvhSTXPcLinQDnHyj56DTLQtL61ki4jo2Loc data=
{'client_port': 9702, 'client_ip': '10.0.0.105', 'alias': 'Node5', 'node_ip': '10.0.0.105', 'node_port': 9701, 'services': ['VALIDATOR'], 'blskey':'2RdajPq6rCidK5gQbMzSJo1NfBMYiS3e44GxjTqZUk3RhBdtF28qEABHRo4MgHS2hwekoLWRTza9XiGEMRCompeujWpX85MPt87WdbTMysXZfb7J1ZXUEMrtE5aZahfx6p2YdhZdrArFvTmFWdojaD2V5SuvuaQL4G92anZ1yteay3R'}``

**Can I use a seed when generating my BLS keys?**

For a new node when using `init_indy_node` if you specify a seed for this script that same seed is used to generate your BLS keys.

**For existing nodes** being upgraded to 1.2.50, which includes state proofs, you would use the script `init_bls_keys` where you can specify a 32-character seed on the command line.

 ``init_bls_keys --name <NODE_ALIAS> --seed '<SEED>'``

After running `init_bls_keys`, Stewards of existing nodes will be required use their CLI node to update their validator's information on the ledger to include the bls keys:

 ``send NODE dest=<node_dest> data={'alias':'<node name>', 'blskey': '<key_generated_by_init_bls_keys>'}``

##### Multi-network and indy_config.py

**Where do I find the configuration file settings?**

With file and folder changes the new location for `indy_config.py` is in the directory location /etc/indy/. The configuration file has a new setting called ``"NETWORK_NAME"`` which is used to identify which network and associated genesis transaction files to use, such as `sandbox` or `live`. If adding a new node to a live pool, change this setting before initializing the node.
The genesis files are now located in their own directory based off the network name "/var/lib/indy/NETWORK_NAME". The defaults are `live`, `local`, and `sandbox`. Setting the ``"NETWORK_NAME"`` in the `indy_config.py` file will determine which network is used. The default setting in the `indy_config.py` file is "``"NETWORK_NAME=sandbox"``.


## 1.1.6 Hot Fix


### Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-plenum | 1.1.27 |
| indy-anoncreds | 1.0.10 |
| indy-node | 1.1.43 |
| release version number | 1.1.6 |
|   |   |   |

### Major Fixes

| Description | Additional Information | Ticket Number |
| --- | --- | --- |
| Added a migration script which eliminates redundant fields with `null` values from legacy transactions in the domain ledger. |   | [INDY-895](https://jira.hyperledger.org/browse/INDY-895) [INDY-869](https://jira.hyperledger.org/browse/INDY-869) |
| Added a constraint on `version` field of `POOL_UPGRADE` transaction that denies values lower than the current installed version. |   | [INDY-895](https://jira.hyperledger.org/browse/INDY-895) [INDY-869](https://jira.hyperledger.org/browse/INDY-869) |
| Added prevention of upgrade to a lower version to `Upgrader` class. |   | [INDY-895](https://jira.hyperledger.org/browse/INDY-895) [INDY-869](https://jira.hyperledger.org/browse/INDY-869) |
| Fixed a bug in `Upgrader` class in search for a `POOL_UPGRADE cancel` transaction for the last `POOL_UPGRADE start` transaction. |   | [INDY-895](https://jira.hyperledger.org/browse/INDY-895) [INDY-869](https://jira.hyperledger.org/browse/INDY-869) |
| Added a test verifying prevention of upgrade to a lower version. |   | [INDY-895](https://jira.hyperledger.org/browse/INDY-895) [INDY-869](https://jira.hyperledger.org/browse/INDY-869) |
| Corrected existing tests according to introduced prevention of upgrade to a lower version. |   | [INDY-895](https://jira.hyperledger.org/browse/INDY-895) [INDY-869](https://jira.hyperledger.org/browse/INDY-869) |
|   |   |   |   |

## 1.1.6


### Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-plenum | 1.1.27 |
| indy-anoncreds | 1.0.10 |
| indy-node | 1.1.37 |
| release version number | 1.1.6 |
|   |   | |

### Major Fixes

| Description | Additional Information | Ticket Number |
| --- | --- | --- |
| Stewards can now demote and promote their own nodes. |   | [INDY-410](https://jira.hyperledger.org/browse/INDY-410) |
| Fixed problem with timezones for timestamp in a transaction. |   | [INDY-466](https://jira.hyperledger.org/browse/INDY-466) |
| Limited incoming message size from 128k to 128MB (Temporary solution). |   | [INDY-25](https://jira.hyperledger.org/browse/INDY-25) |
| Fixed `send CLAIM_DEF` command. |   | [INDY-378](https://jira.hyperledger.org/browse/INDY-378) |
| Masked private information in the CLI logs/output. |   | [INDY-725](https://jira.hyperledger.org/browse/INDY-725) |
| Fixes crashes on ubuntu 17.04. |   | [INDY-8](https://jira.hyperledger.org/browse/INDY-8) |
| Python interpreter is executed in optimized mode. |   | [INDY-211](https://jira.hyperledger.org/browse/INDY-211) |
| Memory leak fixes. |   | [INDY-223](https://jira.hyperledger.org/browse/INDY-223) |
| Some minor stability fixes. |   |   |
| Fixed a problem with migration during manual upgrades. |   | [INDY-808](https://jira.hyperledger.org/browse/INDY-808) |
| Fixed a problem with the message length limitation. This was a permanent solution of [INDY-25](https://jira.hyperledger.org/browse/INDY-25). |   | [INDY-765](https://jira.hyperledger.org/browse/INDY-765) |
| Fixed a problem when the pool was writing transactions when more than F nodes were stopped. |   | [INDY -786](https://jira.hyperledger.org/browse/INDY-786) |
| Fixed a problem when the pool was broken after processing lots of transactions at once. |   | [INDY-760](https://jira.hyperledger.org/browse/INDY-760) |
| Fixed a problem when the pool doesn&#39;t come back to consensus in cases when less than n-f nodes are alive. |   | [INDY-804](https://jira.hyperledger.org/browse/INDY-804) |
| Partially fixed a problem when the pool responded with outdated data. |   | [INDY-761](https://jira.hyperledger.org/browse/INDY-761) |
|   |   |   | |

### Changes - Additions - Known Issues

| Description | Workaround | Ticket |
| --- | --- | --- |
| **New ledger serialization is supported and Leveldb is used as a storage for all ledgers** : msgpack is used for the ledger serialization (both transaction log and merkle tree). |   |   |
| **The new serialization change created changes to the directory structure for the nodes.** The directory name changes are located on a node under .sovrin/data/nodes/&lt;node name&gt;/&lt;directories&gt;. The change removes the ledger files as plain text files and creates them as binary files. A new tool was created to view the ledger entries called `read_ledger`. This tool also provides you with a count of the transactions. To learn more about this tool and to see a list of available commands, run this as the sovrin user: `read_ledger --h` | | |
| **Genesis transaction files are renamed adding a \_genesis to the end of each file name.** |   |   |
| **Added the commands to the POOL\_UPGRADE to support downgrade and re-installation.** However both have issues and should not be used at this time. |   | [INDY-735](https://jira.hyperledger.org/browse/INDY-735) [INDY-755](https://jira.hyperledger.org/browse/INDY-755) |
| **Fixes to upgrade procedure, in particular an issue which caused an infinite loop.** |   | [INDY-316](https://jira.hyperledger.org/browse/INDY-316) |
| **A new CLI command was added to ease the process of rotating a verification key (verkey).** The command is `change current key` or `change current key with seed xxxxx`. |   |   |
| **Improvements to log messages.** |   |   |
| **Publishing only to repo.sovrin.org** |   |   |
|  In your sources.list you only need the entry &quot;deb https://repo.evernym.com/deb xenial stable&quot;. |   |   |
| **Implemented a command line tool to provide validator status.** |   | [INDY-715](https://jira.hyperledger.org/browse/INDY-715) |
| **&quot;Debug&quot; mode for tests was moved to parameter.** |   | [INDY-716](https://jira.hyperledger.org/browse/INDY-716) |
| **Log levels were changed on some debug level messages to an info level.** |   | [INDY-800](https://jira.hyperledger.org/browse/INDY-800) |
| **If the pool loses enough nodes and cannot reach consensus when enough nodes become available, the pool will still not reach consensus.** | If you restart all the nodes in the pool, it will start reaching consensus again. | [INDY-849](https://jira.hyperledger.org/browse/INDY-849) |


## 1.0


### Major Features

| Description |Additional Information |
| ---| ---|
| Identities can be created, and their exercise and evolution can be controlled by ed25519 key pairs (where the public verkey is stored and updated on the ledger). |   |
| Identities are fully self-sovereign by default. Guardianship, as described in _The Sovrin Provisional Trust Framework_, is also possible. | [The Sovrin Provisional Trust Framework](https://sovrin.org/library/)  |
| Identities can have an agent, and the agent endpoint can be declared on the ledger. |   |
| Identities are associated with the roles defined in _The Sovrin Provisional Trust Framework._ Specifically see: trustee, steward, trust anchor, TGB member, and normal identity. | [The Sovrin Provisional Trust Framework](https://sovrin.org/library/) |
| Identities with special roles have the appropriate permissions. | [http://bit.ly/2eRfeIV](http://bit.ly/2eRfeIV) |
| The ledger supports a core set of transaction types suitable for early workflows | [http://bit.ly/2v1OAmO](http://bit.ly/2v1OAmO) |
| Byzantine consensus is robust and transparent, providing diffuse trust. |   |
| Ledger state is enstructured as a Patricia Trie. This is the precondition for implementing Observer nodes and for having high-trust answers with any number of invalid high-trust answers with any number of malicious intermediaries. |   |
| The network generates log files that stewards can use as an audit trail. |   |
| The network can be upgraded without manual tasks for stewards, using a POOL\_UPGRADE transaction. |   |
| Batching is functional, allowing bulk submission and reasonable throughput. (Note, however, that very large batches of more than a few thousand transactions are discouraged because they consume network resources unfairly and may cause brownouts for other users.) |   |
| Simple safeguards are in place to react to hacking and DDoS attempts. |   |
| Terminology in the CLI has been updated to align with usage in the Provisional Trust Framework and the DID specification. In particular, this release is DID-centric, whereas earlier previews were cryptonym-centric. |   |
| Registering simple schemas for claims, and then deriving claims and proofs from them, is possible. This supports many of the use cases envisioned for simple verifiable claims, but not the advanced privacy-preserving features offered by Camenisch-Lysyanskya-style schemes. |   |
|   |   |   |

### Deferred Features

| Description | Additional Information |
| --- | --- |
| **DDO support:** Currently, the DID support in Sovrin uses ATTRIB transactions. This limits the complexity and granularity of identity control. Full DDO support will be necessary to support models such as individual signers being empowered to represent a corporation, for example. |   |
| **Rich schema:** W3C verifiable claim schema thinking is still evolving, and we need time to experiment with the best ways to represent certain constructs. Very complex credential types may not yet be representable. |   |
| **Revocation:** Although the cryptographic foundation for revocation is fully implemented in the anoncreds component that Sovrin depends on, the ledger currently lacks a transaction type to update accumulators. This means initial claims won&#39;t support revocation. |   |
| **Observers and state proofs:** Although the state trie is present in the ledger, code to return a state proof with each answer from the ledger has not yet been released. This means clients need to hear f+1 consistent answers from validator nodes before they can trust any response from the ledger. The [Indy SDK](https://github.com/hyperledger/indy-sdk) supports this type of response checking, and will cut over to using the more efficient state proof mechanism as soon as Sovrin does. Once we have that mechanism in place, observer nodes become trustworthy because they cannot simulate state proofs. This drastically increases the scale and performance of the system. | [Indy SDK](https://github.com/hyperledger/indy-sdk) |
|   |   |   |


### Known Issues

| Description | Workaround | Ticket |
| --- | --- | --- |
| **Stewards demoting a node:** Stewards should be able to demote and promote their own node. Demoting a node removes it from the pool so it does not participate in the pool reaching consensus. |   | [INDY-410](https://jira.hyperledger.org/browse/INDY-410) |
| **Ubuntu 17.04 support:** Currently the CLI and Node are not supported on Ubuntu 17.04. |   | [INDY-8](https://jira.hyperledger.org/browse/INDY-8) |
| **Timezones not matching:** If a system timezone of the primary does not match that of the other nodes the pool cannot function. | **1.** Shut down the sovrin-node service.        **2.** Use "sudo dpkg-reconfigure tzdata" to change your timezone to UTC. (Select "None of the Above" and then "UTC".) | [INDY-466](https://jira.hyperledger.org/browse/INDY-466) |
| **Installing client as root:** Installing the client as root does not allow users to access the transaction files and they are not able to connect to the pool. |   | [INDY-24](https://jira.hyperledger.org/browse/INDY-24) |
|   |   |   |   |

### New Terminology Changes to the CLI

Please be aware that the following terminology has changed:

- . **keyring** is now &quot;wallet&quot;
- . **link** is now &quot;connection&quot;
- . **invitation** is now &quot;request&quot;
- . **identifier** is now &quot;DID&quot;

### Emergencies

For emergencies where the Network is under a DOS attack, there is a new command for Trustees only that will put the Pool into a read-only mode.

    send POOL\_CONFIG writes=False

or optional

    send POOL\_CONFIG writes=False force=False

The following are bool parameters: writes and force. Writes is required. Force is not required. The default for force is False.

### Other Important Information

#### Keyrings to Wallets Directory

With the terminology change from &quot;keyring&quot; to &quot;wallet,&quot; the wallets that have already been created will be the client in the *.sovrin/keyrings/live* directory. When you upgrade, you&#39;ll have to start the CLI and connect to live just once for the wallet directory to be created. The new directory is *.sovrin/wallets/live*. The upgrade **does not** move your existing wallets from */keyrings/live* to */wallets/live* since the wallet directory does not exist until you connect for the first time. You will need to move the existing wallets from */keyrings/live* to */wallets/live* and they will be ready for use.

#### Time Stamps

With the latest builds (which will be on your new stable) you will start to see a time stamp in the ledger for the transactions. It will look like the following:

    V4SGRU86Z58d6TV7PBUe6f|1500090331922505|5iBLSL3iujkNsX4RiGN7RjDk5UVtE8GHsn8XHsRsmhUUN9Jz8GEmYtpPrso8e96jo3YafhYSwPkd9QXYA3wYyst4|1500090332|1|Pqs6Tyj1huDsjKYsiF8w4f|~WwZD24Fk4ka7ZY47MPopoo||||||2||


The time stamp is the entry that shows *1500090332*.

The *1500090332* is the utc epoch and a converter can be found at this site [https://www.epochconverter.com/](https://www.epochconverter.com/) so you can see the human readable version of the time.
