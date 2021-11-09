package org.ignite.config;

import lombok.Value;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.configuration.*;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.ignite.Entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class IgniteConfig {




//    @Bean(destroyMethod = "close")
//    Ignite ignite(IgniteConfiguration igniteConfiguration) throws IgniteException {
//        final Ignite ignite = Ignition.start(igniteConfiguration);
//        // Activate the cluster. Automatic topology initialization occurs
//        // only if you manually activate the cluster for the very first time.
//        ignite.cluster().active(true);
//	    /*// Get all server nodes that are already up and running.
//	    Collection<ClusterNode> nodes = ignite.cluster().forServers().nodes();
//		// Set the baseline topology that is represented by these nodes.
//	    ignite.cluster().setBaselineTopology(nodes);*/
//        return ignite;
//    }





//    @Bean(name = "igniteInstance")
//    public static Ignite igniteInstance(Ignite ignite) {
//       // ignite = Ignition.start("D:/BookShare/src/main/java/org/ignite/config/ignite-config.xml");
//        Ignition.setClientMode(true);
//        ignite.active(true);
//        return ignite;
//    }


}
