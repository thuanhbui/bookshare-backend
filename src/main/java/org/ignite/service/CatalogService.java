package org.ignite.service;

import org.apache.ignite.IgniteCache;
import org.ignite.Dao.CatalogRepository;
import org.ignite.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CatalogService {

    @Autowired CatalogRepository cataDao;


    public List<eCatalogDto> findCatalogByNameCatalog(String name_catalog) {
        List<Cache.Entry<eCatalogKey, eCatalog>> entries = cataDao.findByNameCatalog(name_catalog);
        List<eCatalogDto> catalogDtos = new ArrayList<>();
        for(Cache.Entry<eCatalogKey, eCatalog> entry : entries) {
            catalogDtos.add(new eCatalogDto(entry.getKey(), entry.getValue()));
        }
        return catalogDtos;
    }

    public eCatalogDto findCatalogByKey(Integer catalogId) {
        eCatalogKey key = new eCatalogKey(catalogId, 1);
        IgniteCache<eCatalogKey, eCatalog> cache = cataDao.cache();
        eCatalog value = cache.get(key);
        return new eCatalogDto(key, value);
    }



    public List<eCatalogDto> getListCatalogs() {
        List<Cache.Entry<eCatalogKey, eCatalog>> entries = cataDao.getListCatalogs();
        List<eCatalogDto> catalogDtos = new ArrayList<>();
        for(Cache.Entry<eCatalogKey, eCatalog> entry : entries) {
            catalogDtos.add(new eCatalogDto(entry.getKey(), entry.getValue()));
        }
        return catalogDtos;
    }

    public eCatalogDto updateCatalog(int cataId, eCatalog catalog) {
        eCatalogKey key = new eCatalogKey(cataId, 1);
        IgniteCache cache = cataDao.cache();
        eCatalog catalog1 = (eCatalog) cache.get(key);
        if (catalog.getNameCatalog() != null) catalog1.setNameCatalog(catalog.getNameCatalog());
        cache.replace(key, catalog1);
        return new eCatalogDto(key, catalog1);
    }

    public void deleteCatalog(int cataId) {
        eCatalogKey key = new eCatalogKey(cataId, 1);
        cataDao.deleteById(key);
    }

    public eCatalogDto addCatalog(eCatalog value) {
        eCatalogKey key = new eCatalogKey(UUID.randomUUID().hashCode(), 1);
        cataDao.cache().put(key, value);
        return new eCatalogDto(key, value);
    }

}
