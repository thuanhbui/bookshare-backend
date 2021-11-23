package org.ignite.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.ignite.Dao.AdminRepository;
import org.ignite.Dao.UserRepository;
import org.ignite.Entity.*;
import org.ignite.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ignite.Security.SercutiryConfig;
import javax.cache.Cache;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Entity entity) {
        String userName = entity.getUsername();
        List<Cache.Entry<UserKey, org.ignite.Entity.User>> users = userRepository.findByUsername(userName);
        if (users.size() > 0 ) {
            UserDto userDto = new UserDto(users.get(0).getKey(), users.get(0).getValue());
            if (users.get(0).getValue().getPassword().equals(entity.getPassword())) {
                return ResponseEntity.ok(userDto);
            }
        }
        List<Cache.Entry<Integer, Admin>> admins = adminRepository.findByUsername(userName);
        if (admins.size() > 0 ) {
            AdminDto adminDto = new AdminDto(admins.get(0).getKey(), admins.get(0).getValue());
            if (admins.get(0).getValue().getPassword().equals(entity.getPassword()))
                return ResponseEntity.ok(adminDto);
        }

        return new ResponseEntity<>("USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public ResponseEntity<?> userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        List<Cache.Entry<UserKey, org.ignite.Entity.User>> entries = userRepository.findByUsername(userName);
        if (entries.size() > 0 ) {
            UserDto userDto = new UserDto(entries.get(0).getKey(), entries.get(0).getValue());
            return ResponseEntity.ok(userDto);
        }
        List<Cache.Entry<Integer, Admin>> admins = adminRepository.findByUsername(userName);
        if (admins.size() > 0 ) {
            AdminDto adminDto = new AdminDto(admins.get(0).getKey(), admins.get(0).getValue());
            return ResponseEntity.ok(adminDto);
        }

        return ResponseEntity.ok("Không tìm thấy username phù hợp !");
    }



}
