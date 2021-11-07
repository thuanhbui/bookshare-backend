package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Service.UserService;
import model.Key.UserKey;
import model.dto.UserDto;

@RequestMapping("/users")
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<UserDto> users = userService.getListUser();   
        System.out.println("ádfg");
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int userKey) {
        System.out.println(userKey);
        return null;
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
