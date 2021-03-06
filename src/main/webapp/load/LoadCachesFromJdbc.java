package load;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.cache.CacheException;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.IgniteDataStreamerTimeoutException;
import org.apache.ignite.Ignition;

import model.Entity.eCatalog;

public class LoadCachesFromJdbc {
	public static void main(String[] args) {
		System.out.println(">>> Stream Loading caches from JDBC SELECT...");
		getLoadCaches();
	}

	public static void getLoadCaches() {
		String loadQuery = "";
		Connection conn = null;
		Statement loadStmt = null;
		LoadCachesFromJdbc loadCaches = new LoadCachesFromJdbc();

		System.out.println(">>> Start Ignite Client...");
		try (Ignite ignite = Ignition.start("./eBookshare-backend/config/example-ignite.xml")) {

			System.out.println(">>> Get Connection to source database...");
			try {
				conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			/**
			 * Office Table
			 */
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> SELECT FROM ProductLine Table, Stream to OfficeCache...");
			loadQuery = "SELECT catalog_id, name_catalog, admin_id FROM eCatalog";

			// If we can get the cache, then lets try to get from JDBC and Stream to it.
			try (IgniteDataStreamer<String, eCatalog> streamer = ignite.dataStreamer("eCatalog")) {
				System.out.println(">>> try to create statement and execute query:" + loadQuery + "...");
				int n = 0; // record counter
				try {
					loadStmt = conn.createStatement();
					ResultSet rs = loadStmt.executeQuery(loadQuery);

					while (rs.next()) {
						String k = null;
						eCatalog v = null;
						try {
							k = rs.getString("eCatalog_id");
							v = new eCatalog(rs.getString("name_catalog"));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
						streamer.addData(k, v);
						n++;
					}
				} catch (SQLException e) {
					System.out.println("Caught SQLException: " + e);
				} finally {
					System.out.println(">>> Load Office count: " + n);
					if (loadStmt != null) {
						try {
							loadStmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch (IgniteDataStreamerTimeoutException e) {
				System.out.println("IgniteDataStreamerTimeoutException: " + e);
			} catch (CacheException e) {
				System.out.println("CacheException: " + e);
			}
		}

	}

}
