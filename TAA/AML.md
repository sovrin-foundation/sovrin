# Acceptance Mechanism List (AML)
```
"Version string":"0.1",
{  
   "product_eula":"The agreement was included in the software product’s terms and conditions as part of a license to the end user.",
   "service_agreement":"The agreement was included in the terms and conditions the user accepted as part of contracting a service.",
   "at_submission":"The agreement was reviewed by the user and accepted at the time of submission of this transaction.",
   "for_session":"The agreement was reviewed by the user and accepted at some point in the user’s session prior to submission.",
   "wallet_agreement":"The agreement was reviewed by the user and this affirmation was persisted in the user’s wallet for use during submission.",
   "on_file":"An authorized person accepted the agreement, and such acceptance is on file with the user’s organization."
}
```
# Mechanisms Descriptions
1. **Product Eula**: The agreement was included in the software product’s terms and conditions as part of a license to the end user. The client software presents the transaction author agreement to the user as part of the product’s end user license agreement. At that time, the user can review the agreement, and the date of this review is stored along with a hash of the agreement at the time. If the agreement changes, the user will review and accept the agreement along with the EULA, or a different acceptance mechanism would be used for the review. Commercial products will likely use this approach with enterprise clients who do a legal review before purchasing.
2. **Service Agreement**: The agreement was included in the terms and conditions the user accepted as part of contracting a service. This interaction is intended for cloud services that interact with the ledger. The service might not have any user interface, but the customer will accept the agreement when signing up for the service.
3. **At Submission**: The agreement was reviewed by the user and accepted at the time of submission of this transaction.
4. **For Session**: The agreement was reviewed by the user and accepted at some point in the user’s session prior to submission. At the time of preparing and submitting the transaction, the agreement was displayed to the user and the user was prompted to review it and agree to it. When the user attempts to write to the ledger, they are given the opportunity to review and accept the agreement. This acceptance is then stored and reused for any other writes during the current session. This is the UX paradigm implemented in the Indy CLI.
5. **Wallet Agreement**: The agreement was reviewed by the user and this affirmation was persisted in the user’s wallet for use during submission. When the user attempts to write to the ledger, the client application presents the agreement for review and acceptance. The date of acceptance is then persisted within the wallet where it can be reused as often as necessary until a new agreement is put on the ledger which the user must review and accept.
6. **On File**: An authorized person accepted the agreement, and such acceptance is on file with the user’s organization. If the user of the client software is representing an organization, the user might not be authorized to accept legal agreements on behalf of that organization. In this case, the client software should prompt the user to enter the date when the authorized person (such as legal counsel) accepted the agreement, and indicate which version of the agreement is being kept on file with the user’s organization so that the user can be prompted if the agreement changes and requires additional review.
