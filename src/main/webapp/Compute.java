import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.affinity.Affinity;

public class Compute {
     public static void main(String args[]) {
        Ignition.setClientMode(true);
        try(Ignite client = Ignition.start("bookshare-backend/config/example-ignite.xml")) {
            calculateTotalViewers(client, "test"); 
        }
    }

    public static void calculateTotalViewers(Ignite client, String test) {
        Affinity<String> affinity = client.affinity("book");
        int partition = affinity.partition(test);
        

    } 
}
