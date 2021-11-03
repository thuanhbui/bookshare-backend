
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.stream.StreamReceiver;

import model.Entity.Admin;
import model.Entity.Catalog;

public class KeyValue {

    public static void main(String args[]) {
        Ignition.setClientMode(true);
        
        Ignite client = Ignition.start("./bookshare-backend/config/example-ignite.xml");
        

        IgniteCache<Integer, Catalog> catalogCache = client.cache("catalog");

        getPutCatalog(catalogCache);
        
        client.close();
    }
    

    private static void getPutCatalog(IgniteCache<Integer, Catalog> catalogCache) {
        Catalog catalog1 = catalogCache.get(1);
        System.out.println("hello " + catalog1.getCatalog());
    

        
    }

    private static void updateSingleField(IgniteCache<String, Admin> adminCache) {
        
    }

/*
private static class AdminEntryProcessor implements EntryProcessor<String, Admin, String> {

        @Override public String process(MutableEntry<String, Admin> entry,
            Object... arguments) throws EntryProcessorException {

            entry.getValue().setUsername("Boris Johnson");

            return entry.getValue().getUsername();
        }
    }*/

}
