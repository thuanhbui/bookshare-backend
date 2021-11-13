package org.ignite.controller;

import org.ignite.Entity.Admin;
import org.ignite.Entity.AdminDto;
import org.ignite.Entity.AdminMapper;
import org.ignite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.cache.Cache;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@RestController
public class AdminCotroller {
    @Autowired
    private AdminService adminService;

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable Integer id) {
        AdminDto adminDto = adminService.findAdminById(id);
        return ResponseEntity.ok(adminDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminDto>> getAllAdmin() {
        List<AdminDto> admins = adminService.getListAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<AdminDto>> findByUsername(@PathVariable String username) {
        List<AdminDto> amins = adminService.findAdminByUsername(username);
        return ResponseEntity.ok(amins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateNameAdmin(@PathVariable Integer id, @RequestBody AdminDto adminDto) {
        AdminDto adminDto1 = adminService.updateNameAdmin(id, adminDto.getUsername());
        return ResponseEntity.ok(adminDto1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdminDto> deleteAdmin(@PathVariable Integer id) {
        AdminDto admin = adminService.findAdminById(id);
        adminService.deleteAdmin(id);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("")
    public ResponseEntity<AdminMapper> createAdmin(@RequestBody Admin admin) {
        AdminMapper adminMapper = new AdminMapper();
        adminMapper.toAdminDto(admin);
        adminService.addAdmin(admin);
        return ResponseEntity.ok(adminMapper);
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
