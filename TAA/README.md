# Transaction Author Agreement Information
The Transaction Author Agreement (TAA) informs users of their rights and obligations when using any of the Sovrin networks. It also clarifies the role of Stewards as data processors. As a result, the Sovrin Governance Working Group voted to require Transaction Authors to accept the TAA with every write to the ledger. The TAA outlines the policies that users must follow when interacting with the Sovrin Ledger. When a user’s client software is preparing a transaction for submission to the network, it must include a demonstration that the user had the opportunity to review the current TAA and accept it. To accept the agreement, the write transaction must include the date of acceptance, the user experience mechanism used to accept (see AML.md in this folder), and either the text of the agreement or a hash as specified below.

# How to Compute a TAA Digest
The transaction author agreement digest is the concatenation of the `version` and the `text` of the TAA on the ledger, with whitespace trimmed. The digest is represented on the ledger as a sha256 hex string often referred to as the "digest hash".

Here is Python sample code for computing the digest: `sha256('{}{}'.format(version, text).encode()).hexdigest()`

# Current TAA Digests
- Version 2.0
  - Ratified December 4, 2019
  - Hash: `"8cee5d7a573e4893b08ff53a0761a22a1607df3b3fcd7e75b98696c92879641f"`

  - Added to MainNet :
  - Added to TestNet :
  - Added to BuilderNet : February 4, 2020

- Version 1
  - Ratified June, 2019
  - Hash: `"920e68ff43bf157d657e35fca291baa00f8b14c395cddf6e4b4e156391baf7cd"`

  - Added to MainNet : Not added.
  - Added to TestNet : Not added.
  - Added to BuilderNet : June 2019.
