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
        Cache.Entry<Integer, Admin> entry = adminDao.findById(admin_id);
        return new AdminDto(entry.getKey(), entry.getValue());
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
        Cache.Entry<Integer, Admin> entry = adminDao.findById(adminId);
        entry.getValue().setUsername(admin.getUsername());
        entry.getValue().setPassword(admin.getPassword());
        entry.getValue().setRegistered_date(admin.getRegistered_date());
        adminDao.save(entry.getKey(), entry.getValue());
        return new AdminDto(entry.getKey(), entry.getValue());
    }

    public void deleteAdmin(int adminId) {
        adminDao.deleteById(adminId);
    }

    public void addAdmin(Admin value) {
        adminDao.save(value);
    }

}
