package org.ignite.controller;

import org.ignite.Entity.*;
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


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.findUserById(id);
        if (userDto == null) return (ResponseEntity<UserDto>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getListUsers();
        if (userDtos == null) return (ResponseEntity<List<UserDto>>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> findByUsername(@RequestParam (value = "username") String username) {
        List<UserDto> userDtos = userService.findUserByUsername(username);
        if (userDtos == null) return (ResponseEntity<List<UserDto>>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(userDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @Valid @RequestBody User user)  {
        UserDto userDto = userService.findUserById(id);
        if (userDto == null) return (ResponseEntity<UserDto>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        userService.updateUser(id, user);
        return ResponseEntity.ok(userDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer id) {
        UserDto userDto = userService.findUserById(id);
        if (userDto == null) return (ResponseEntity<UserDto>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        userService.deleteUser(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        List<UserDto> foundUser = userService.findUserByUsername(user.getUsername().trim());
        if (foundUser.size() > 0 ) {
            return (ResponseEntity<UserDto>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        UserDto userDto = userService.addUser(user);

        return ResponseEntity.ok(userDto);
    }
}
