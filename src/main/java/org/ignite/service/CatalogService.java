package org.ignite.service;

import org.ignite.Dao.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
//    @Autowired
//    private IgniteConfig igniteConfig;
//
//    private IgniteCache<eCatalogKey, eCatalog> getCache() {
//        if (igniteConfig.client != null) {
//            return igniteConfig.client.cache("catalog");
//        } else {
//            return igniteConfig.ignite().cache("catalog");
//        }
//    }
//   IgniteCache<eCatalogKey, eCatalog> cataCache = igniteConfig.ignite().cache("catalog");
//

    @Autowired CatalogRepository cataDao;


    public List<?> getListCatalogs() {
        List<?> catas = cataDao.getListCatalogs();
        return catas;
    }


//    public eCatalogDto updateCatalog(int catalog_id, String name_catalog) {
//        Cache.Entry<eCatalogKey, eCatalog> entry = cataDao.findById(catalog_id);
//        entry.getValue().setNameeCatalog(name_catalog);
//        cataDao.save(entry.getKey(), entry.getValue());
//
//        return new eCatalogDto(entry.getKey(), entry.getValue());
//    }

    public List<?> findCatalogById(int catalog_id) {
        List<?> entries = cataDao.findCatalogById(catalog_id);
        return entries;
    }


    public List<List<?>> searchCatalog(String keyword) {
//        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM eCatlog WHERE name_catalog LIKE \'%" + keyword + "%\';");
//        List<List<?>> res = cataCache.query(qry.setDistributedJoins(true)).getAll();
//        if (res != null) return res;
//        throw new NotFoundException("Không tìm thấy sách");
//        List<List<?>> entries = cataDao.searchCatalog(keyword == null ? null : keyword);
        return  null;
    }

}
