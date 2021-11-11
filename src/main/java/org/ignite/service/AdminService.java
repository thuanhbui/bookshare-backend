package org.ignite.service;

import org.apache.ignite.IgniteCache;
import org.ignite.Dao.AdminRepository;
import org.ignite.Entity.Admin;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AdminService {

//    @Autowired
//    private IgniteConfig igniteConfig;
//
//    private IgniteCache<Integer, Admin> getCache() {
//        if (igniteConfig.client != null) {
//            return igniteConfig.client.cache("admin");
//        } else {
//            return igniteConfig.ignite().cache("admin");
//        }
//    }
//    IgniteCache<Integer, Admin> adminCache = igniteConfig.ignite().cache("admin");
//    SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM ADMIN");
//    List<List<?>> res = adminCache.query(qry.setDistributedJoins(true)).getAll();
//
//
//
//
//
    @Inject
    AdminRepository adminRepository;

    public List<List<?>> getListAdmin() {
//        final List<Admin> admins = adminRepository.getListAdmin();
        List<List<?>> admins = adminRepository.getListAdmin();
        return admins;
    }

    protected IgniteCache<String, Admin> getAlertsCache() {
        return null;
    }


}
