# Sovrin Release Notes





![image alt text](banner.png)





* [Aries 1.1.7](aries-1.1.7)

* [Aries 1.1.6 Hot Fix](#aries-1.1.6-hot-fix)

* [Aries 1.1.6](#aries-1.1.6)

* [Aries 1.0](#aries-1.0)






#### Disclosure

Although every attempt has been made to make this information as accurate as possible, please know there may be things that are omitted, not fully developed yet, or updates since this publication that were not included in the information below. Only the most pressing or significant items have been listed. For the entire list of tickets and or specific information about any given item, please visit the list at [Hyperleder Indy's Jira](https://jira.hyperledger.org/). Once logged in, simply navigate to Projects > Indy.

## Aries 1.1.7


Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-plenum | 1.2.29 |
| indy-anoncreds | 1.0.11 |
| indy-node | 1.2.50 |
| sovrin | 1.1.7 |
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

1. Send `Pool Upgrade` command so all nodes upgrade.

2. Sometime later each Steward will need to run the following command line to add their BLS Keys:

Steward should run from `indy` user script `enable_bls` (placed in /usr/local/bin):

`enable_bls --name=<node-name> --node_dest=<node-dest-as-in-node-txn> --steward_seed=<seed-used-to-create-steward-did> --bls_seed=<32 character seed-for-bls-key>`

#### Questions and Answers

##### BLS Keys for State Proofs


**What does BLS stand for?**

Boneh-Lynn-Shacham - The BLS signature scheme is used to verify that a signer is authentic.

**How does the CLI use State Proof for confirmation?**

When the CLI requests information about a transaction is checks the BLS signatures to verify the transaction was written by nodes that are part of the validator pool. The CLI sends a request to one node (arbitrary one). If the Reply doesn't have a State Proof, or the reply is incorrect/invalid, then CLI falls back to sending requests to all Nodes and waiting for f+1 equal Replies.

**What if not all nodes in the pool have BLS signing keys for a transaction?**

Transactions only get signed if all nodes reaching consensus can sign it (>= n-f Nodes with correct BLS signatures).

**Can the bls_seed be any 32 character seed like the Steward seed?**

Yes.

**When adding a new node to an existing pool where do I find my BLS key?**

When initializing your node using `init_indy_node` the output will display the keys for the node including the BLS key. It can be found in /var/lib/indy/<network_name>/keys/<node_name>/bls_keys/bls_pk file (e.g.: /var/lib/indy/sandbox/keys/Node1/bls_keys/bls_pk)

When you send the transaction to add the new node to the pool it will also contain the BLS key in the transaction shown in this example.

*Example of send node command with BLS for 5th AWS node:*

`send NODE dest=4Tn3wZMNCvhSTXPcLinQDnHyj56DTLQtL61ki4jo2Loc data=
{'client_port': 9702, 'client_ip': '10.0.0.105', 'alias': 'Node5', 'node_ip': '10.0.0.105', 'node_port': 9701, 'services': ['VALIDATOR'], 'blskey':'2RdajPq6rCidK5gQbMzSJo1NfBMYiS3e44GxjTqZUk3RhBdtF28qEABHRo4MgHS2hwekoLWRTza9XiGEMRCompeujWpX85MPt87WdbTMysXZfb7J1ZXUEMrtE5aZahfx6p2YdhZdrArFvTmFWdojaD2V5SuvuaQL4G92anZ1yteay3R'}`

**Can I use a seed when generating my BLS keys?**

For a new node when using `init_indy_node` if you specify a seed for this script that same seed is used to generate your BLS keys.

For existing nodes being upgraded to the new version using state proofs you would use the script `enable_bls` where you can specify a seed on the command line.

`enable_bls --name=<node-name> --node_dest=<;node-dest-as-in-node-txn> --steward_seed=<seed-used-to-create-steward-did> --bls_seed=<32 character seed-for-bls-key>
`

##### Multi-network and indy_config.py

**Where do I find the configuration file settings?**

With file and folder changes the new location for `indy_config.py` is in the directory location /etc/indy/. The configuration file has a new setting called ``"NETWORK_NAME"`` which is used to identify which network and associated genesis transaction files to use like `sandbox` or `live`. If adding a new node to a live pool change this setting before initializing the node.
The genesis files are now located in their own directory based off the network name "/var/lib/indy/NETWORK_NAME". The defaults are `live`, `local`, and `sandbox`. Setting the ``"NETWORK_NAME"`` in the `indy_config.py` file will determine which network is used. The default setting in the `indy_config.py` file is "``"NETWORK_NAME=sandbox"``.


## Aries 1.1.6 Hot Fix


Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-plenum | 1.1.27 |
| indy-anoncreds | 1.0.10 |
| indy-node | 1.1.43 |
| sovrin | 1.1.6 |
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

## Aries 1.1.6


Component Version Information

| Components | Version Numbers |
| --- | --- |
| indy-plenum | 1.1.27 |
| indy-anoncreds | 1.0.10 |
| indy-node | 1.1.37 |
| sovrin | 1.1.6 |
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


## Aries 1.0


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
