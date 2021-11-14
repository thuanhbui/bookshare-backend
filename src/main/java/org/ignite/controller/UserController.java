package org.ignite.controller;

import org.ignite.Entity.*;
import org.ignite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.findUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> userDtos = userService.getListUsers();
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<UserDto>> findByUsername(@PathVariable String username) {
        List<UserDto> userDtos = userService.findUserByUsername(username);
        return ResponseEntity.ok(userDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody User user) {
        UserDto userDto = userService.updateUser(id, user);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer id) {
        UserDto userDto = userService.findUserById(id);
        userService.deleteUser(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("")
    public ResponseEntity<UserMapper> createUser(@RequestBody User user, int adminID) {
        UserMapper userMapper = new UserMapper();
        userMapper.toUserDto(user);
        userService.addUser(user, adminID);
        return ResponseEntity.ok(userMapper);
    }
}
