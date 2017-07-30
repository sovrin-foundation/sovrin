# Sovrin Release Notes





![image alt text](banner.png)



## Aries 1.0


#### Disclosure

Although every attempt has been made to make this information as accurate as possible, please know there may be things that are omitted, not fully developed yet, or updates since this publication that were not included in the information below. Only the most pressing or significant items have been listed. For the entire list of tickets and or specific information about any given item, please visit the list at [Hyperleder Indy's Jira](https://jira.hyperledger.org/). Once logged in, simply navigate to Projects > Indy.


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
