package org.ignite.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.ignite.Dao.AdminRepository;
import org.ignite.Dao.UserRepository;
import org.ignite.Entity.Admin;
import org.ignite.Entity.AdminDto;
import org.ignite.Entity.UserDto;
import org.ignite.Entity.UserKey;
import org.ignite.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.cache.Cache;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
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

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

}
