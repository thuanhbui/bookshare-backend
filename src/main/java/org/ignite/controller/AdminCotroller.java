package org.ignite.controller;

import org.ignite.Entity.Admin;
import org.ignite.Entity.AdminDto;
import org.ignite.Entity.AdminMapper;
import org.ignite.Entity.UserDto;
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
    public ResponseEntity<?> findByUsername(@RequestParam (value = "username") String username) {
        List<AdminDto> admins = adminService.findAdminByUsername(username);
        return ResponseEntity.ok(admins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
        AdminDto adminDto = adminService.updateAdmin(id, admin);
        return ResponseEntity.ok(adminDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdminDto> deleteAdmin(@PathVariable Integer id) {
        System.out.println("delete");
        AdminDto admin = adminService.findAdminById(id);
        if (admin != null) {
            System.out.println("delete success");
            adminService.deleteAdmin(id);
            return ResponseEntity.ok(admin);
        }
        return (ResponseEntity<AdminDto>) ResponseEntity.status(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        List<AdminDto> foundUser = adminService.findAdminByUsername(admin.getUsername().trim());
        if (foundUser.size() > 0) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Đã có tên người dùng này trong hệ thống");
        }

        AdminDto adminDto = adminService.addAdmin(admin);

        return ResponseEntity.ok(adminDto);
    }



}