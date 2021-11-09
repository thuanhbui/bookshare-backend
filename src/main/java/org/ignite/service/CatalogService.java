package org.ignite.service;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.ignite.Entity.Admin;
import org.ignite.Entity.User;
import org.ignite.Entity.eCatalog;
import org.ignite.Entity.eCatalogKey;
import org.ignite.config.IgniteConfig;
import org.ignite.exception.NotFoundException;
import org.ignite.model.dto.UserDto;
import org.ignite.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CatalogService {
    @Autowired
    private Ignite ignite;

    IgniteCache<eCatalogKey, eCatalog> cataCache = ignite.cache("catalog");
    SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM ECATALOG");
    List<List<?>> res = cataCache.query(qry.setDistributedJoins(true)).getAll();

    ArrayList<eCatalog> catas = new ArrayList<eCatalog>((Collection<? extends eCatalog>) res);


    public List<eCatalog> getListCatalog() {
        return null;
    }



    public List<eCatalog> searchCatalog(String keyword) {
        List<eCatalog> result = new ArrayList<>();
//        for(eCatalog cata : catas) {
//            if (cata.getNameeCatalog().contains(keyword)) {
//                result.add(cata);
//            }
//        }
        return result;
    }

}
