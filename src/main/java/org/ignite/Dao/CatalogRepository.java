package org.ignite.Dao;

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

    //@Query("Select * from eCatalog")
    public List<Cache.Entry<eCatalogKey, eCatalog>> findByNameCatalog(String name_catalog);

    @Query("SELECT * FROM eCatalog WHERE catalogId = ?")
    public Cache.Entry<eCatalogKey, eCatalog> findById(Integer id);


}
