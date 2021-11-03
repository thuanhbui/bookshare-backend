import javax.cache.Cache;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.stream.StreamReceiver;

import model.Entity.Catalog;

public class TestIgnite {
    public static void main(String args[]) {
        Ignition.setClientMode(true);
        
        Ignite client = Ignition.start("./bookshare-backend/config/example-ignite.xml");
        
        IgniteCache<String, Catalog> cache = client.getOrCreateCache("catalog");


        IgniteBiPredicate<String, Catalog> filter = (key, p) -> p.getCatalog().equals("Nhiếp ảnh, dựng phim");

        try (QueryCursor<Cache.Entry<String, Catalog>> qryCursor = cache.query(new ScanQuery<>(filter))) {
            qryCursor.forEach(
                entry -> System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()));
}
        
        /*      
        try (IgniteDataStreamer<String, Catalog> stmr = client.dataStreamer("catalog")) {
            // Stream entries.
            stmr.allowOverwrite(true);
            stmr.receiver((StreamReceiver<String, Catalog>) (cache, entries) -> entries.forEach(entry -> {

                // do something with the entry
                System.out.println(entry.getKey() + ' ' + entry.getValue());
                cache.put(entry.getKey(), entry.getValue());
            }));
        }
        System.out.println("dataStreamerExample output:");
        //IgniteCache<String, Catalog> catalogCache = client.cache("catalog");

        //getPutCatalog(catalogCache);*/
        
        client.close();
    }
}
