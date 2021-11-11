package org.ignite.controller;

import org.ignite.Entity.Admin;
import org.ignite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminCotroller {
    @Autowired
    private AdminService adminService;



//    @GetMapping("")
//    public ResponseEntity<?> getListAdmin() {
//        List<List<?>> admins = adminService.getListAdmin();
//        return ResponseEntity.ok(admins);
//    }

    @GetMapping

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
