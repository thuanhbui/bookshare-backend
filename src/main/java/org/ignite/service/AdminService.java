package org.ignite.service;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.ignite.Entity.Admin;
import org.ignite.Entity.eBook;
import org.ignite.Entity.eBookKey;
import org.ignite.config.IgniteConfig;
import org.ignite.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private Ignite ignite;


    IgniteCache<Integer, Admin> adminCache = ignite.cache("admin");
    SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM ADMIN");
    List<List<?>> res = adminCache.query(qry.setDistributedJoins(true)).getAll();
    private static final Logger logger = LoggerFactory.getLogger(Admin.class);




    public List<List<?>> getListAdmin() {

        return null;
    }

    protected IgniteCache<String, Admin> getAlertsCache() {
        return null;
    }


}
