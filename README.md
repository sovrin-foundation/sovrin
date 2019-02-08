![logo](banner.png)
# The Sovrin Foundation

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
light, but the [API](https://github.com/hyperledger/indy-sdk/tree/master/libindy/src/api) is
liberally commented... You may also want to work through Indy's
[Getting Started Guide](https://github.com/hyperledger/indy-node/blob/stable/getting-started.md),
which was originally written for Sovrin and then genericized.

If you want to contribute to
Sovrin, it's likely that you'll want to do so via [indy-node](https://github.com/hyperledger/indy-node).
Bugs, stories, and backlog for Indy are managed in
[Hyperledger's Jira](https://jira.hyperledger.org/projects/INDY).
Use project name `INDY` (or `IS` for the Indy SDK).



## How to Add Documentation
For new features and pull requests, maintainers should make sure that the **contributor has added an explanation for their changes in the docs folder before merging the PR.**
  
Contributors should write an addition to a current file or add a new file to the docs/source/ folder that explains what their feature is and how it works. If needed, they may also add a link to more technical README's located nearer to the code.

Whenever additions are made to the docs, make sure to update the `index.rst` in whichever folder the file has been added, and build the docs locally to confirm they work (TODO: add the `sphinx-build` command to our CI/CD flow).

For example, if I wanted to add another file to the indy-sdk docs/ folder named `glossary.md`, I would create the file, and then add a reference to it in the `index.rst`: 
```
.. toctree::
  :maxdepth: 1
  :hidden:

  getting-started/index.rst
  ...
  other files
  ...
  glossary.md                   .. <-- this is your new file!

```

To add a new file to a subfolder, simply update the subfolder's `index.rst` with the relative link to your file.

If you'd like to link to a file outside of the docs/ folder, you'll need to provide an external github link (this is by design, to keep our docs organized into a single folder)


## Building the docs on your machine

Here are the quick steps to achieve this on a local machine without depending on ReadTheDocs. Note: Instructions may differ depending on your OS.
Run these commands within the repository folder
```bash
pip install Sphinx
pip install sphinx_rtd_theme
pip install recommonmark==0.4.0
make html
```

This will generate all the html files in `_build/html` which you can then browse locally in your browser. Every time you make a change to the documentation you will need to rerun `make html`.

## Additional Instructions
This section is to be used for repo maintainers to add additional documentation guidelines or instructions. 