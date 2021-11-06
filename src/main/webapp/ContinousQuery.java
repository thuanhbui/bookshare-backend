import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryUpdatedListener;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.query.ContinuousQuery;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import model.Entity.Catalog;
import model.Key.CatalogKey;

public class ContinousQuery {
    public static void main(String args[]) {
        Ignition.setClientMode(true);

        Ignite client = Ignition.start("eBookshare-backend/config/ignite-config.xml");

        subscribeForDataUpdates(client);

        SqlFieldsQuery query = new SqlFieldsQuery(
                "UPDATE admin SET password = 'XZ123' " + "WHERE username = 'tran hien'");

        client.cache("admin").query(query);

        System.out.println("Updated the admin record");
    }

    private static void subscribeForDataUpdates(Ignite client) {
        ContinuousQuery<BinaryObject, BinaryObject> query = new ContinuousQuery<>();

        query.setLocalListener(new ChangesListener());
        query.setRemoteFilterFactory(new PopulationChangesFilter());

        client.cache("admin").withKeepBinary().query(query);

        System.out.println("Subscribed for the notifications");
    }

    private static class ChangesListener implements CacheEntryUpdatedListener<BinaryObject, BinaryObject> {
        @Override
        public void onUpdated(Iterable<CacheEntryEvent<? extends BinaryObject, ? extends BinaryObject>> events)
                throws CacheEntryListenerException {

            for (CacheEntryEvent<? extends BinaryObject, ? extends BinaryObject> event : events) {
                CatalogKey key = event.getKey().deserialize();
                Catalog value = event.getValue().deserialize();

                System.out.println("Admin record has been changed [key=" + key + ", value = " + value + ']');
            }
        }
    }

    private static class PopulationChangesFilter implements Factory<CacheEntryEventFilter<BinaryObject, BinaryObject>> {
        @Override
        public CacheEntryEventFilter<BinaryObject, BinaryObject> create() {
            return new CacheEntryEventFilter<BinaryObject, BinaryObject>() {
                @Override
                public boolean evaluate(CacheEntryEvent<? extends BinaryObject, ? extends BinaryObject> e) {
                    // Notify the application only if the population has been changes.
                    /**
                     * DEMO_TODO: notify the application only if the value of the population field
                     * gets changed. For instance, if you execute the following query via GridGain
                     * Control Center or by other means the application will be updated as well -
                     * `UPDATE City SET name = 'LA' WHERE id = 3794;`. We don't want this to happen
                     * and would rather react to the changes of the population.
                     *
                     * Refer to {@see App4ContinousQueries} of the `complete` project for a final
                     * solution.
                     */
                    return true;
                }
            };
        }
    }
}