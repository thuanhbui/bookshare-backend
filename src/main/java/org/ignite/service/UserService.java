package org.ignite.service;

import org.ignite.Dao.UserRepository;
import org.ignite.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.cache.Cache;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserDto> findUserByUsername(String name) {
        List<Cache.Entry<UserKey, User>> entries = userRepository.findByUsername(name);
        List<UserDto> userDtos = new ArrayList<>();
        for(Cache.Entry<UserKey, User> entry : entries) {
            userDtos.add(new UserDto(entry.getKey(), entry.getValue()));
        }
        return userDtos;
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
        Cache.Entry<UserKey, User> entry = userRepository.findById(userID);
        entry.getValue().setUsername(user.getUsername());
        entry.getValue().setAvatar(user.getPassword());
        entry.getValue().setEmail(user.getEmail());
        entry.getValue().setPhone(user.getPhone());
        entry.getValue().setAvatar(user.getAvatar());
        entry.getValue().setRegisteredDate(user.getRegisteredDate());
        userRepository.save(entry.getKey(), entry.getValue());
        return new UserDto(entry.getKey(), entry.getValue());
    }

    public void deleteUser(int userID) {
        UserKey key = new UserKey(userID, 1);
       // userRepository.deleteById(key);
    }

    public void addUser(User value) {
        userRepository.save(value);
    }

}
