package org.ignite.Security;

import org.ignite.Dao.AdminRepository;
import org.ignite.Dao.UserRepository;
import org.ignite.Entity.Admin;
import org.ignite.Entity.User;
import org.ignite.Entity.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

@Service
public class IgniteUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Cache.Entry<Integer, Admin> entryAdmin =  adminRepository.findByUsername(s);

        Cache.Entry<UserKey, User> entryUser =  userRepository.findByUsername(s);


        if (entryAdmin != null) {
            Admin admin = entryAdmin.getValue();
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(admin.getUsername()).password(admin.getPassword()).roles("ADMIN").build();
            return userDetails;
        }


        if (entryUser != null) {
            User user = entryUser.getValue();
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).roles("USER").build();
            return userDetails;
        }

        throw new UsernameNotFoundException("User " + s + " was not found in the database");
    }
}
