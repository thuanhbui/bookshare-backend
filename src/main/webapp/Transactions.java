import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteTransactions;
import org.apache.ignite.Ignition;
import org.apache.ignite.transactions.Transaction;
import org.apache.ignite.transactions.TransactionConcurrency;
import org.apache.ignite.transactions.TransactionIsolation;

import model.Entity.User;
import model.Key.UserKey;

public class Transactions {

    public static void main(String args[]) {
        Ignition.setClientMode(true);
        
        try(Ignite client = Ignition.start("bookshare-backend/config/example-ignite.xml")) {
            IgniteCache<UserKey, User> userCache = client.cache("user");

            updateAdminUsername(client, userCache);
        }
    
    }

    private static void updateAdminUsername(Ignite client, IgniteCache<UserKey, User> userCache ) {
        IgniteTransactions txs = client.transactions();

        UserKey bookshareKey = new UserKey(1, 1);
        UserKey XZKey = new UserKey(2, 1);

        try(Transaction tx = txs.txStart(TransactionConcurrency.PESSIMISTIC, TransactionIsolation.REPEATABLE_READ)) {
            User bookshare = userCache.get(bookshareKey);
            User XZ = userCache.get(XZKey);

            System.out.println(bookshare + " " + XZ);

            tx.commit();

            System.out.println("Transaction has been committed");
        }
    }

}
