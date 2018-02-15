![logo](banner.png)

Sovrin is a global utility for self-sovereign identity--that is, an identity that nobody controls
except its natural owner, that can't be taken away or stripped of its privacy or manipulated
through unreasonable terms of service. Imagine if you could bring your identity with you to
all your digital interactions, instead of creating new logins for every online bank, every mobile
app, every social network, every email client, every government agency, every shopping site...
Imagine if they logged in to you, instead of the other way around... Sovrin uses distributed ledger
(blockchain) technology to achieve this freedom, and it leverages very sophisticated cryptography
to make it all secure and private.

Sovrin's technical underpinnings
come from [Hyperledger](https://hyperledger.org)'s [Indy project](https://github.com/hyperledger/indy-node).
Sovrin is a specific instantiation of Indy, using a governance model
described in the [Sovrin Provisional Trust Framework](http://bit.ly/svrn-ptrustfw).
Thus, the code that's stored here is mostly a thin veneer on top of Indy--just enough to provide
genesis transactions for the particular machines that bootstrapped Sovrin, plus some light
utilities for those who run Sovrin.

To interact with the Sovrin community, visit us at [sovrin.slack.com](https://sovrin.slack.com),
or at [forum.sovrin.org](https://forum.sovrin.org).

To report vulnerabilities against Sovrin, email security@sovrin.org or visit us on
[hackerone](https://hackerone.com/sovrin_foundation).

If you'd like to write code against Sovrin, we recommend that you check out
[indy-sdk](https://github.com/hyperledger/indy-sdk); it offers a C-callable library
plus convenience wrappers in java, python, .NET, and more. Documentation is currently
light, but the [API](https://github.com/hyperledger/indy-sdk/tree/master/src/api) is
liberally commented... You may also want to work through Indy's
[Getting Started Guide](https://github.com/hyperledger/indy-node/blob/stable/getting-started.md),
which was originally written for Sovrin and then genericized.

If you want to contribute to
Sovrin, it's likely that you'll want to do so via [indy-node](https://github.com/hyperledger/indy-node).
Bugs, stories, and backlog for Indy are managed in
[Hyperledger's Jira](https://jira.hyperledger.org/projects/INDY).
Use project name `INDY` (or `IS` for the Indy SDK).
