package org.ignite.controller;

import org.ignite.Entity.*;
import org.ignite.service.ImageStorageService;
import org.ignite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageStorageService storageService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.findUserById(id);
        if (userDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không có người dùng này trong hệ thống");
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> userDtos = userService.getListUsers();
        if (userDtos == null)
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hiện tại, chưa có người dùng nào trong hệ thống");
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("")
    public ResponseEntity<?> findByUsername(@RequestParam (value = "username") String username) {
        List<UserDto> userDtos = userService.findUserByUsername(username);
        if (userDtos == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng trong hệ thống");
        return ResponseEntity.ok(userDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody User user)  {
        UserDto userDto = userService.findUserById(id);
        if (userDto == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng trong hệ thống");
        if (!user.getAvatarMulti().isEmpty()) user.setAvatar(storageService.storeFile(user.getAvatarMulti()));
        userService.updateUser(id, user);
        return ResponseEntity.ok(userDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        UserDto userDto = userService.findUserById(id);
        if (userDto == null)
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng trong hệ thống");
        userService.deleteUser(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        List<UserDto> foundUser = userService.findUserByUsername(user.getUsername().trim());
        if (foundUser.size() > 0 ) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Đã có tên người dùng này trong hệ thống");
        }
        UserDto userDto = userService.addUser(user);
        return ResponseEntity.ok(userDto);
    }
}
