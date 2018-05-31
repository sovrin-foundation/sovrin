from common.serializers.serialization import pool_state_serializer
from plenum.common.txn_util import transform_to_new_format
from storage import store_utils

srcs = [
    'domain_transactions_live_genesis',
    'domain_transactions_local_genesis',
    'domain_transactions_sandbox_genesis',
    'pool_transactions_live_genesis',
    'pool_transactions_local_genesis',
    'pool_transactions_sandbox_genesis'
]
for src in srcs:
    with open(src, 'r') as f1:
        with open(src + "_1", 'w') as f2:
            i = 1
            for line in store_utils.cleanLines(f1):
                txn = pool_state_serializer.deserialize(line)
                new_val = transform_to_new_format(txn=txn, seq_no=int(i))
                f2.write(pool_state_serializer.serialize(new_val, toBytes=False))
                f2.write('\n')
                i += 1
