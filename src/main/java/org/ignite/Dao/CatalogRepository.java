package org.ignite.Dao;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.Query;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.ignite.Entity.eCatalog;
import org.ignite.Entity.eCatalogKey;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;


@Repository
@RepositoryConfig(cacheName = "catalog1")
public interface CatalogRepository extends IgniteRepository<eCatalog, eCatalogKey>{


//    @Query("SELECT * FROM ECATALOG")
//    public List<List<?>> getListCatalog();
//
//    @Query("SELECT * FROM eCatalog WHERE name_catalog = ? ")
//    public List<List<?>> searchCatalog(String keyword);

    @Query("SELECT * FROM eCatalog WHERE catalog_id = ?")
    public Cache.Entry<eCatalogKey, eCatalog> findById(int catalog_id);

}