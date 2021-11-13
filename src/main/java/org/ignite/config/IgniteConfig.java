package org.ignite.config;


import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;


import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.apache.ignite.springframework.boot.autoconfigure.IgniteConfigurer;
import org.ignite.Entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableIgniteRepositories
public class IgniteConfig {
    @Bean(name = "igniteInstance")
    public Ignite igniteInstance(Ignite ignite) {
        return ignite;
    }

    @Bean
    public IgniteConfigurer configurer() {
        return igniteConfiguration -> {
            igniteConfiguration.setClientMode(true);
//            DataStorageConfiguration storageCfg = new DataStorageConfiguration();
//
//
//            DataRegionConfiguration defaultRegion = new DataRegionConfiguration();
//            defaultRegion.setMaxSize(150 * 1024 * 1024);
//            defaultRegion.setMetricsEnabled(true);
//            defaultRegion.setPersistenceEnabled(true);
//
//            storageCfg.setDefaultDataRegionConfiguration(defaultRegion);
//            storageCfg.setMetricsEnabled(true);
//
//            storageCfg.setDefaultDataRegionConfiguration(defaultRegion);
//
//
//            igniteConfiguration.setDataStorageConfiguration(storageCfg);

        };
    }
}