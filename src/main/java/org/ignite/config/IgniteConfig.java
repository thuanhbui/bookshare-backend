package org.ignite.config;


import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;


import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.springframework.boot.autoconfigure.IgniteConfigurer;
import org.ignite.Entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;


@Configuration
public class IgniteConfig {

    @Bean(name = "igniteInstance")
    public Ignite igniteInstance(Ignite ignite) {
        ignite = Ignition.start("D:/BookShare/src/main/java/org/ignite/config/ignite-config.xml");
        return ignite;
    }

    @Bean
    public IgniteConfigurer configurer() {
        return igniteConfiguration -> {
            igniteConfiguration.setClientMode(true);
        };
    }


//    public Ignite client;
//
//    @Bean(destroyMethod = "close")
//    public  Ignite ignite() throws IgniteException {
//        //Ignite.close();
//        Ignition.setClientMode(false);
//        final Ignite ignite = Ignition.start("D:/BookShare/src/main/java/org/ignite/config/example-data-regions.xml");
//        // Activate the cluster. Automatic topology initialization occurs
//        // only if you manually activate the cluster for the very first time.
//       // final Ignite ignite = Ignition.start();
//        this.client = ignite;
//        ignite.cluster().active(true);
//	    /*// Get all server nodes that are already up and running.
//	    Collection<ClusterNode> nodes = ignite.cluster().forServers().nodes();
//		// Set the baseline topology that is represented by these nodes.
//	    ignite.cluster().setBaselineTopology(nodes);*/
//        return ignite;
//    }





//    @Bean(name = "igniteInstance")
//    public static Ignite igniteInstance(Ignite ignite) {
//       // ignite = Ignition.start("D:/BookShare/src/main/java/org/ignite/config/example-data-regions.xml");
//        Ignition.setClientMode(true);
//        ignite.active(true);
//        return ignite;
//    }

//    @Bean(name = "igniteInstance")
//    public Ignite igniteInstance(Ignite ignite) {
//        ignite.active(true);
//        return ignite;
//    }
//
//    @Bean
//    public IgniteConfigurer configurer() {
//        return igniteConfiguration -> {
//            CacheConfiguration cacheUsers = new CacheConfiguration("user");
//            cacheUsers.setIndexedTypes(UserKey.class, User.class);
//
//            CacheConfiguration cacheAdmin = new CacheConfiguration("admin");
//            cacheAdmin.setIndexedTypes(Integer.class, Admin.class);
//
//            CacheConfiguration cacheBooks = new CacheConfiguration("eBook");
//            cacheUsers.setIndexedTypes(eBookKey.class, eBook.class);
//
//            CacheConfiguration cacheCatalog = new CacheConfiguration("catalog");
//            cacheAdmin.setIndexedTypes(eCatalogKey.class, eCatalog.class);
//
//
//
//            igniteConfiguration.setCacheConfiguration(cacheUsers, cacheAdmin, cacheBooks, cacheCatalog);
//        };
//    }


}
