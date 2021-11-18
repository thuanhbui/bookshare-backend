package org.ignite.service;

import org.apache.ignite.IgniteCache;
import org.ignite.Dao.AdminRepository;
import org.ignite.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class AdminService {

    @Autowired AdminRepository adminDao;



    public List<AdminDto> findAdminByUsername(String name) {
        List<Cache.Entry<Integer, Admin>> entries = adminDao.findByUsername(name);
        List<AdminDto> admins = new ArrayList<>();
        for(Cache.Entry<Integer, Admin> entry : entries) {
            admins.add(new AdminDto(entry.getKey(), entry.getValue()));
        }
        return admins;
    }

    public AdminDto findAdminById(int admin_id) {
        IgniteCache<Integer, Admin> cache = adminDao.cache();
        Admin admin = cache.get(admin_id);
        return new AdminDto(admin_id, admin);
    }

    public List<AdminDto> getListAdmins() {
        List<Cache.Entry<Integer, Admin>> entries = adminDao.getListAdmins();
        List<AdminDto> admins = new ArrayList<>();
        for(Cache.Entry<Integer, Admin> entry : entries) {
            admins.add(new AdminDto(entry.getKey(), entry.getValue()));
        }
        return admins;
    }

    public AdminDto updateAdmin(int adminId, Admin admin) {
        IgniteCache<Integer, Admin> cache = adminDao.cache();
        Admin admin1 = cache.get(adminId);
        if (admin.getUsername() != null) admin1.setUsername(admin.getUsername());
        if (admin.getPassword() != null) admin1.setPassword(admin.getPassword());
        cache.replace(adminId, admin1);
        return new AdminDto(adminId, admin1);
    }

    public void deleteAdmin(int adminId) {
        System.out.println("delete success 2");
        adminDao.deleteByAdminId(adminId);
    }

    public Admin addAdmin(Admin value) {
        return adminDao.save(value);
    }

}