package sql;

import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import model.Entity.Catalog;

public class SqlDML {
    private static final String ADMIN_CACHE = SqlDML.class.getSimpleName() + "admin";
    private static final String USER_CACHE = SqlDML.class.getSimpleName() + "user";
    private static final String eCatalog_CACHE = SqlDML.class.getSimpleName() + "eCatalog";
    private static final String eBook_CACHE = SqlDML.class.getSimpleName() + "eBook";
    private static final String eBookSHELF_CACHE = SqlDML.class.getSimpleName() + "eBookshelf";
    private static final String HISTORIES_CACHE = SqlDML.class.getSimpleName() + "histories";

    /**
     * Executes example.
     *
     * @param args Command line arguments, none required.
     * @throws Exception If example execution failed.
     */
    // @SuppressWarnings({"unused", "ThrowFromFinallyBlock"})

    /*
     * public static void main(String[] args) throws Exception { try (Ignite ignite
     * = Ignition.start("examples/config/example-ignite.xml")) {
     * print("Cache query DML example started.");
     * 
     * CacheConfiguration<Long, Organization> orgCacheCfg = new
     * CacheConfiguration<>(ORG_CACHE); orgCacheCfg.setIndexedTypes(Long.class,
     * Organization.class);
     * 
     * CacheConfiguration<Long, eCatalog> personCacheCfg = new
     * CacheConfiguration<>(PERSON_CACHE);
     * personCacheCfg.setIndexedTypes(Long.class, Person.class);
     * 
     * // Auto-close cache at the end of the example. try ( IgniteCache<Long,
     * Organization> orgCache = ignite.getOrCreateCache(orgCacheCfg);
     * IgniteCache<Long, Person> personCache =
     * ignite.getOrCreateCache(personCacheCfg) ) { insert(orgCache, personCache);
     * select(personCache, "Insert data");
     * 
     * update(personCache); select(personCache, "Update salary for Master degrees");
     * 
     * delete(personCache); select(personCache, "Delete non-Apache employees"); }
     * finally { // Distributed cache could be removed from cluster only by
     * #destroyCache() call. ignite.destroyCache(PERSON_CACHE);
     * ignite.destroyCache(ORG_CACHE); }
     * 
     * print("Cache query DML example finished."); } }
     * 
     * /** Populate cache with test data.
     *
     * @param orgCache Organization cache,
     * 
     * @param personCache Person cache.
     */
    /*
     * private static void insert(IgniteCache<Integer, eCatalog> orgCache,
     * IgniteCache<Long, eCatalog> personCache) { // Insert organizations.
     * SqlFieldsQuery qry = new
     * SqlFieldsQuery("insert into Organization (_key, id, name) values (?, ?, ?)");
     * 
     * orgCache.query(qry.setArgs(1L, 1L, "ASF")); orgCache.query(qry.setArgs(2L,
     * 2L, "Eclipse"));
     * 
     * // Insert persons. qry = new SqlFieldsQuery(
     * "insert into Person (_key, id, orgId, firstName, lastName, salary, resume) values (?, ?, ?, ?, ?, ?, ?)"
     * );
     * 
     * personCache.query(qry.setArgs(1L, 1L, 1L, "John", "Doe", 4000, "Master"));
     * personCache.query(qry.setArgs(2L, 2L, 1L, "Jane", "Roe", 2000, "Bachelor"));
     * personCache.query(qry.setArgs(3L, 3L, 2L, "Mary", "Major", 5000, "Master"));
     * personCache.query(qry.setArgs(4L, 4L, 2L, "Richard", "Miles", 3000,
     * "Bachelor")); }
     * 
     * /** Example of conditional UPDATE query: raise salary by 10% to everyone who
     * has Master degree.
     *
     * @param personCache Person cache.
     */
    /*
     * private static void update(IgniteCache<Long, eCatalog> personCache) { String
     * sql = "update Person set salary = salary * 1.1 " + "where resume = ?";
     * 
     * personCache.query(new SqlFieldsQuery(sql).setArgs("Master")); }
     * 
     * /** Example of conditional DELETE query: delete non-Apache employees.
     *
     * @param personCache Person cache.
     */
    /*
     * private static void delete(IgniteCache<Long, eCatalog> personCache) { String
     * sql = "delete from Person where orgId != ?";
     * 
     * personCache.query(new SqlFieldsQuery(sql).setArgs(1)).getAll(); }
     * 
     * /** Query current data.
     *
     * @param personCache Person cache.
     * 
     * @param msg Message.
     */
    /*
     * private static void select(IgniteCache<Long, eCatalog> personCache, String
     * msg) { String sql =
     * "select p.id, concat(p.firstName, ' ', p.lastName), o.name, p.resume, p.salary "
     * + "from Person as p, \"" + eCatalog_CACHE + "\".Organization as o " +
     * "where p.orgId = o.id";
     * 
     * List<List<?>> res = personCache.query(new
     * SqlFieldsQuery(sql).setDistributedJoins(true)).getAll();
     * 
     * print(msg);
     * 
     * for (Object next : res) System.out.println(">>>     " + next); }
     * 
     * /** Prints message.
     *
     * @param msg Message to print before all objects are printed.
     */
    /*
     * private static void print(String msg) { System.out.println();
     * System.out.println(">>> " + msg); }
     */
}