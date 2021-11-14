package org.ignite.service;

import org.ignite.Dao.CatalogRepository;
import org.ignite.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {

    @Autowired CatalogRepository cataDao;


    public List<eCatalogDto> findCatalogByName(String name) {
        List<Cache.Entry<eCatalogKey, eCatalog>> entries = cataDao.findByNameCatalog(name);
        List<eCatalogDto> catalogDtos = new ArrayList<>();
        for(Cache.Entry<eCatalogKey, eCatalog> entry : entries) {
            catalogDtos.add(new eCatalogDto(entry.getKey(), entry.getValue()));
        }
        return catalogDtos;
    }

    public eCatalogDto findCatalogById(int catalog_id) {
        Cache.Entry<eCatalogKey, eCatalog> entry = cataDao.findById(catalog_id);
        return new eCatalogDto(entry.getKey(), entry.getValue());
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
        Cache.Entry<eCatalogKey, eCatalog> entry = cataDao.findById(cataId);
        entry.getValue().setNameCatalog(catalog.getNameCatalog());
        cataDao.save(entry.getKey(), entry.getValue());
        return new eCatalogDto(entry.getKey(), entry.getValue());
    }

    public void deleteCatalog(int cataId) {
        eCatalogKey key = new eCatalogKey(cataId, 1);
        cataDao.deleteById(key);
    }

    public void addCatalog(eCatalog value) {
        cataDao.save(value);
    }

}
