package API;
import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import model.Entity.eBook;
import model.Entity.eCatalog;
import model.Entity.User;
import model.Key.eBookKey;
import model.Key.eCatalogKey;
import model.Key.UserKey;

public class API {

    public static void main(String args[]) {
        Ignition.setClientMode(true);

        Ignite client = Ignition.start("bookshare-backend/config/ignite-config.xml");

        // IgniteCache<eBookKey, eBook> bookCache = client.cache("eBook");
        // eBook book = new eBook("Công phá vật lý 12", null, null, null, "Việt
        // Nam", null, "2021-01-01", 0);
        // eBookKey bookKey = new eBookKey("CP12", 1);
        // insertEBook(client, book, bookKey);
        // List<List<?>> newT = getCatalogs(client);
        // List<List<?>> new = getNewEBook(client);
        // for(Object next : newT) {
        // System.out.println(" " + next);
        // }
        UserKey newUserKey = new UserKey(3, 1);
        User newUser = new User("NCT", "09876", "1@gmail.com", "098765", "VietNam", null);
        insertUser(client, newUserKey, newUser);
    }

    // getData
    public static List<List<?>> getCatalogs(Ignite client) {

        IgniteCache<eCatalogKey, eCatalog> cataCache = client.cache("catalog");
        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT name_catalog FROM Catalog;");

        List<List<?>> res = cataCache.query(qry.setDistributedJoins(true)).getAll();

        return res;
    }

    public static List<List<?>> getNewEBook(Ignite client) {

        IgniteCache<eBookKey, eBook> bookCache = client.cache("eBook");
        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT title FROm eBook "
                + "WHERE last_update < (SELECT MAX(last_update) FROM eBook) "
                + "OR last_update = (SELECT MAX(last_update) FROM eBook) " + "ORDER BY last_update DESC LIMIT 5;");
        return bookCache.query(qry.setDistributedJoins(true)).getAll();

    }

    // insert Data
    public static void insertEBook(Ignite client, eBook book, eBookKey bookKey) {
        IgniteCache<eBookKey, eBook> bookCache = client.cache("eBook");
        SqlFieldsQuery qry = new SqlFieldsQuery(
                "INSERT INTO eBook(admin_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        bookCache.query(qry.setArgs(bookKey.geteBookID(), book.getTitle(), book.getDescription(), book.getImageLink(),
                book.getFileLink(), book.getLanguage(), book.getReleaseYear(), book.getLastUpdate(), book.getViewers(),
                bookKey.getUserID()));
        System.out.println("Insert Book Success!");
    }

    public static void insertUser(Ignite client, UserKey userKey, User user) {
        IgniteCache<UserKey, User> userCache = client.cache("user");
        SqlFieldsQuery qry = new SqlFieldsQuery(
                "INSERT INTO user(user_id, username, password, email, phone, address, registered_date, admin_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        userCache.query(qry.setArgs(userKey.getUserID(), user.getUsername(), user.getPassword(), user.getEmail(),
                user.getPhone(), user.getAddress(), user.getRegisteredDate(), userKey.getAdminID()));
        System.out.println("Insert User Success!");
    }

}
