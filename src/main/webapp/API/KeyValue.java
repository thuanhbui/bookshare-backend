package API;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;

import org.apache.ignite.Ignition;

import model.Entity.Admin;
import model.Entity.eCatalog;
import model.Entity.User;
import model.Key.UserKey;

public class KeyValue {

    public static void main(String args[]) {
        Ignition.setClientMode(true);

        Ignite client = Ignition.start("./bookshare-backend/config/ignite-config.xml");

        // IgniteCache<Integer, User> eCatalogCache = client.cache("admin");

        // getPuteCatalog(eCatalogCache);

        IgniteCache<UserKey, User> userCache = client.cache("user");
        tra(userCache);

        client.close();
    }

    public static Admin getAdmin(IgniteCache<Integer, Admin> adminCache, int admin_id) {
        return adminCache.get(admin_id);
    }

    public static User getUser(IgniteCache<UserKey, User> userCache, UserKey userKey) {
        return userCache.get(userKey);
    }

    private static void getPuteCatalog(IgniteCache<Integer, User> eCatalogCache) {
        User eCatalog1 = eCatalogCache.get(1);
        System.out.println("hello " + eCatalog1.getUsername());

    }

    private static void tra(IgniteCache<UserKey, User> userCache) {
        UserKey userKey = new UserKey(1, 1);
        User user = userCache.get(userKey);
        System.out.println("hello " + user.getUsername());
    }

}
