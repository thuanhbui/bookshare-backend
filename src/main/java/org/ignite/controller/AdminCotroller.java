package org.ignite.controller;

import org.ignite.Entity.Admin;
import org.ignite.Entity.AdminDto;
import org.ignite.Entity.AdminMapper;
import org.ignite.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


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
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> admins = adminService.getListAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("")
    public ResponseEntity<List<AdminDto>> findByUsername(@RequestParam (value = "username") String username) {
        List<AdminDto> amins = adminService.findAdminByUsername(username);
        return ResponseEntity.ok(amins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
        AdminDto adminDto = adminService.updateAdmin(id, admin);
        return ResponseEntity.ok(adminDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdminDto> deleteAdmin(@PathVariable Integer id) {
        AdminDto admin = adminService.findAdminById(id);
        if (admin != null) {
            System.out.println("delete success");
            adminService.deleteAdmin(id);
            return ResponseEntity.ok(admin);
        }
        return (ResponseEntity<AdminDto>) ResponseEntity.status(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<AdminMapper> createAdmin(@RequestBody Admin admin) {
        AdminMapper adminMapper = new AdminMapper();
        adminMapper.toAdminDto(admin);
        adminService.addAdmin(admin);
        return ResponseEntity.ok(adminMapper);
    }



}