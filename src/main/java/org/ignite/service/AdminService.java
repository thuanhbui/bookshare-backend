package org.ignite.service;

import org.apache.ignite.IgniteCache;
import org.ignite.Dao.AdminRepository;
import org.ignite.Entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired AdminRepository adminDao;

    public List<?> findAdminById(int admin_id) {
        List<?> entry = adminDao.findAdminById(admin_id);
        System.out.println(entry.toArray());
        return entry;
    }


    public List<?> getListAdmins() {
        List<?> admins = adminDao.getListAdmins();
        return admins;
    }

//    public Admin findByAdminName(String userName) {
//        Admin admin = adminRepository.findByAdminName(userName);
//        return admin;
//    }

    protected IgniteCache<String, Admin> getAlertsCache() {
        return null;
    }


}
