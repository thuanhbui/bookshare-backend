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
        Cache.Entry<UserKey, User> entry1 =  userRepository.findByUsername(s);
        User user = entry1.getValue();
        if (user != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).roles("USER").build();
            return userDetails;
        }
        Cache.Entry<Integer, Admin> entry2 =  adminRepository.findByUsername(s);
        Admin admin = entry2.getValue();
        if (admin != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(admin.getUsername()).password(admin.getPassword()).roles("ADMIN").build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User " + s + " was not found in the database");
    }
}
