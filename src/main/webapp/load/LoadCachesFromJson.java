package load;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

import model.Entity.Catalog;

public class LoadCachesFromJson {
    private static final Properties props = new Properties();
    static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static void main(String[] args) throws IgniteException, IOException {
        String dataLocation = "./bookshare-backend/sql/"; // default location
        DecimalFormat numFormat = (DecimalFormat)NumberFormat.getCurrencyInstance();
        String symbol = numFormat.getCurrency().getSymbol();
        numFormat.setNegativePrefix("-"+symbol);
        numFormat.setNegativeSuffix("");

        try (InputStream in = IgniteConfiguration.class.getClassLoader().getResourceAsStream("sales.properties")) {
            props.load(in);
            dataLocation = props.getProperty("dataLocation");
            System.out.println(">>>>>>>>>>>>>>>>> loaded properties sales.properties; dataLocation set to: " + dataLocation);
        }
        catch (Exception ignored) {
            System.out.println(">>>>>>>>>>>>>>>>> Failed loading properties; using default dataLocation: " + dataLocation);
        }

        try (Ignite ignite = Ignition.start("./bookshare-backend/config/example-ignite.xml")){

            System.out.println(">>> JSON Stream Loading caches:");

            /*
             * ------------------------------------------------------------------------------------------------------------
             * catalog.json
             * ------------------------------------------------------------------------------------------------------------
             */
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> catalogCache...");
            //Reader reader = Files.newBufferedReader(Paths.get(dataLocation + "catalog.json"), StandardCharsets.UTF_8);

            JsonFactory factory = new JsonFactory();

            //read json file data to String
    		byte[] jsonData = Files.readAllBytes(Paths.get(dataLocation + "catalog.json"));
    		
            ObjectMapper mapper = new ObjectMapper(factory);
            JsonNode CatalogNode = mapper.readTree(jsonData);  

            try (IgniteDataStreamer<Integer, Catalog> streamer = ignite.dataStreamer("catalog")){

	            Iterator<Map.Entry<String,JsonNode>> catalogIterator = CatalogNode.fields();
	            while (catalogIterator.hasNext()) {
	
	                Map.Entry<String,JsonNode> rootNode = catalogIterator.next();
	                System.out.println("Key: " + rootNode.getKey() + "\tValue:" + rootNode.getValue());

	                JsonNode CatalogRootValue = rootNode.getValue();
	                String CatalogRootKey = rootNode.getKey();
	                
	                if (CatalogRootKey.equalsIgnoreCase("catalog")) {

	    	           	int n = 0; // record counter
	    	           	Iterator<JsonNode> Catalog = CatalogRootValue.elements();
	    	           	//Iterator<Map.Entry<String,JsonNode>> catalogIterator = catalogValue.fields();
	    	            while (Catalog.hasNext()) {
	                    	JsonNode catalog = Catalog.next();
	                    	
			                Integer k = 1;//ParseTypes.parseInteger(catalog.get("catalog_id").asText());
                            Catalog v = null;
		                    try {
		                        v = new Catalog(
                                    catalog.get("catalog_name").toString());
		                    } catch (NumberFormatException e) {
		                        // TODO Auto-generated catch block
		                        e.printStackTrace();
		                    }
		                    streamer.addData(k, v);
	    	            } // End while catalog array iterator
	                } // end if this is a catalog entry
	            } // End while catalog iterator

            }

            System.out.println(">>> JSON Stream Loading caches - COMPLETE!!!");

        }
    }

}
