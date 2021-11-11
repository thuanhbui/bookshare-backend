package org.ignite.service;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.ignite.Dao.CatalogRepository;
import org.ignite.Entity.Admin;
import org.ignite.Entity.User;
import org.ignite.Entity.eCatalog;
import org.ignite.Entity.eCatalogKey;
import org.ignite.config.IgniteConfig;
import org.ignite.exception.NotFoundException;
import org.ignite.model.dto.UserDto;
import org.ignite.model.dto.eCatalogDto;
import org.ignite.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.Collection;
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

    @Autowired
    CatalogRepository cataDao;


//    public List<List<?>> getListCatalog() {
//       // IgniteCache<eCatalogKey, eCatalog> cataCache = Ca
////        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM ECATALOG");
////        List<List<?>> res = cataCache.query(qry.setDistributedJoins(true)).getAll();
////        if (res != null) return null;
////        throw new NotFoundException("Không có nào Catalog trong hệ thống");
//        List<List<?>> catas = cataDao.getListCatalog();
//        return null;
//    }


//    public eCatalogDto updateCatalog(int catalog_id, String name_catalog) {
//        Cache.Entry<eCatalogKey, eCatalog> entry = cataDao.findById(catalog_id);
//        entry.getValue().setNameeCatalog(name_catalog);
//        cataDao.save(entry.getKey(), entry.getValue());
//
//        return new eCatalogDto(entry.getKey(), entry.getValue());
//    }

    public eCatalogDto findById(int catalog_id) {
        Cache.Entry<eCatalogKey, eCatalog> entry = cataDao.findById(catalog_id);
        return new eCatalogDto(entry.getKey(), entry.getValue());
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
