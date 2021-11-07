package sql;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;

public class SqlDDL {
    private static final String DUMMY_CACHE_NAME = "dummy_cache";

    /**
     * Executes example.
     *
     * @param args Command line arguments, none required.
     * @throws Exception If example execution failed.
     */
    @SuppressWarnings({ "unused", "ThrowFromFinallyBlock" })
    public static void main(String[] args) throws Exception {
        try (Ignite ignite = Ignition.start("./eBookshare-backend/config/ignite-config.xml")) {
            print("Cache query DDL example started.");

            // Create dummy cache to act as an entry point for SQL queries (new SQL API
            // which do not require this
            // will appear in future versions, JDBC and ODBC drivers do not require it
            // already).
            CacheConfiguration<?, ?> cacheCfg = new CacheConfiguration<>(DUMMY_CACHE_NAME).setSqlSchema("PUBLIC");

            try (IgniteCache<?, ?> cache = ignite.getOrCreateCache(cacheCfg)) {
                // Create reference City table based on REPLICATED template.
                cache.query(new SqlFieldsQuery("DROP TABLE IF EXISTS admin;" + 
                        "CREATE TABLE admin(" + 
                            "admin_id INT(15) PRIMARY KEY, " +
                            "username CHAR(32), " + 
                            "password CHAR(32), " +
                            "registered_date DATE " + 
                        ")WITH \"TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=admin, VALUE_TYPE=model.Entity.Admin\";"))
                        .getAll();

                cache.query(new SqlFieldsQuery(
                        ""))
                        .getAll();

                // Create table based on PARTITIONED template with one backup.
                cache.query(new SqlFieldsQuery(
                        "CREATE TABLE person (id LONG, name VARCHAR, city_id LONG, PRIMARY KEY (id, city_id)) "
                                + "WITH \"backups=1, affinity_key=city_id\""))
                        .getAll();

                print("Created database objects.");

                SqlFieldsQuery qry = new SqlFieldsQuery("INSERT INTO city (id, name) VALUES (?, ?)");

                cache.query(qry.setArgs(1L, "Forest Hill")).getAll();
                cache.query(qry.setArgs(2L, "Denver")).getAll();
                cache.query(qry.setArgs(3L, "St. Petersburg")).getAll();

                qry = new SqlFieldsQuery("INSERT INTO person (id, name, city_id) values (?, ?, ?)");

                cache.query(qry.setArgs(1L, "John Doe", 3L)).getAll();
                cache.query(qry.setArgs(2L, "Jane Roe", 2L)).getAll();
                cache.query(qry.setArgs(3L, "Mary Major", 1L)).getAll();
                cache.query(qry.setArgs(4L, "Richard Miles", 2L)).getAll();

                print("Populated data.");

                List<List<?>> res = cache.query(
                        new SqlFieldsQuery("SELECT p.name, c.name FROM Person p INNER JOIN City c on c.id = p.city_id"))
                        .getAll();

                print("Query results:");

                for (Object next : res)
                    System.out.println(">>>    " + next);

                cache.query(new SqlFieldsQuery("drop table Person")).getAll();
                cache.query(new SqlFieldsQuery("drop table City")).getAll();

                print("Dropped database objects.");
            } finally {
                // Distributed cache can be removed from cluster only by #destroyCache() call.
                ignite.destroyCache(DUMMY_CACHE_NAME);
            }

            print("Cache query DDL example finished.");
        }
    }

    /**
     * Prints message.
     *
     * @param msg Message to print before all objects are printed.
     */
    private static void print(String msg) {
        System.out.println();
        System.out.println(">>> " + msg);
    }
}
