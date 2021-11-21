package org.ignite.service;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.ignite.Dao.UserRepository;
import org.ignite.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto findUserByUsername(String name) {
        Cache.Entry<UserKey, User> entry = userRepository.findByUsername(name);
        return new UserDto(entry.getKey(), entry.getValue());
    }

    public UserDto findUserById(int userId) {
        Cache.Entry<UserKey, User> entry = userRepository.findById(userId);
        return new UserDto(entry.getKey(), entry.getValue());
    }

    public List<UserDto> getListUsers() {
        List<Cache.Entry<UserKey, User>> entries = userRepository.getListUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for(Cache.Entry<UserKey, User> entry : entries) {
            userDtos.add(new UserDto(entry.getKey(), entry.getValue()));
        }
        return userDtos;
    }



    public UserDto updateUser(int userID, User user) {
        UserKey key = new UserKey(userID, 1);
        IgniteCache cache = userRepository.cache();
        User user1 = (User) cache.get(key);
        if (user.getUsername() != null) user1.setUsername(user.getUsername());
        if (user.getPassword() != null) user1.setPassword(user.getPassword());
        if (user.getEmail() != null)    user1.setEmail(user.getEmail());
        if (user.getPhone() != null) user1.setPhone(user.getPhone());
        if (user.getAvatar() != null) user1.setAvatar(user.getAvatar());
        cache.replace(key, user1);
        return new UserDto(key, user1);
    }

    public void deleteUser(int userID) {
        userRepository.deleteByUserId(userID);
    }

    public UserDto addUser(User value) {
        UserKey key = new UserKey(UUID.randomUUID().hashCode(), 1);
        userRepository.cache().put(key, value);
        return new UserDto(key, value);
    }

}
