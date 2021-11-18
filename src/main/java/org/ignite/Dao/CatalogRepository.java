package org.ignite.Dao;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.eCatalog;
import org.ignite.Entity.eCatalogKey;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.List;


@Repository
@RepositoryConfig(cacheName = "catalog")
public interface CatalogRepository extends IgniteRepository<eCatalog, eCatalogKey>{


    @Query("SELECT * FROM ECATALOG")
    public List<Cache.Entry<eCatalogKey, eCatalog>> getListCatalogs();

    public List<Cache.Entry<eCatalogKey, eCatalog>> findByNameCatalog(String name_catalog);


}
