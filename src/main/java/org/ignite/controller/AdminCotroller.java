package org.ignite.controller;

import org.ignite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminCotroller {
    @Autowired
    private AdminService adminService;

    @GetMapping("/{admin_id}")
    public ResponseEntity<?> getById(@PathVariable int admin_id) {
        List<?> adminDto = adminService.findAdminById(admin_id);
        return ResponseEntity.ok(adminDto);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAdmin() {
        List<?> admins = adminService.getListAdmins();
        return ResponseEntity.ok(admins);
    }





    @PostMapping("")
    public ResponseEntity<?> createCatalog() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCatalog() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCatalog() {
        return null;
    }
}
