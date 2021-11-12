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
@RepositoryConfig(cacheName = "catalog1")
public interface CatalogRepository extends IgniteRepository<eCatalog, eCatalogKey>{


    @Query("SELECT catalog_id, name_catalog, eBook_id FROM ECATALOG")
    public List<?> getListCatalogs();
//
//    @Query("SELECT * FROM eCatalog WHERE name_catalog = ? ")
//    public List<?> searchCatalog(String keyword);

    @Query("SELECT catalog_id, name_catalog, eBook_id FROM eCatalog WHERE catalog_id = ?")
    public List<?> findCatalogById(int catalog_id);

}
