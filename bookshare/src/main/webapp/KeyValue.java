
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.gridgain.examples.model.Country;

public class KeyValue {

    public static void main(String args[]) {
        Ignition.setClientMode(true);

        Ignite client = Ignition.start("bookshare-backend/config/ignite-config.xml");
        IgniteCache<String, Admin> adminCache = client.cache("Admin");

        getPutAdmin(adminCache);


        client.close();
    }
    

    private static void getPutAdmin(IgniteCache<String, Admin> adminCache) {
        Admin admin = adminCache.get("admin1");
        System.out.println("Welcome to Ignite");
        
    }

    private static void updateSingleField(IgniteCache<String, Admin> adminCache) {
        
    }


private static class AdminEntryProcessor implements EntryProcessor<String, Admin, String> {

        @Override public String process(MutableEntry<String, Admin> entry,
            Object... arguments) throws EntryProcessorException {

            entry.getValue().setUsername("Boris Johnson");

            return entry.getValue().getUsername();
        }
    }

}
