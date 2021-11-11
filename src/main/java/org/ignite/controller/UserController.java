package org.ignite.controller;

import org.ignite.model.dto.UserDto;
import org.ignite.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam(name = "keyword", required = false, defaultValue = "") String name) {
        List<List<?>>  users = userService.searchUser(name);
        return ResponseEntity.ok(users);
    }

    @GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<List<?>> users = userService.getListUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDto result = userService.getUserById(id);

        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser() {

        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser() {
        return null;
    }
}
