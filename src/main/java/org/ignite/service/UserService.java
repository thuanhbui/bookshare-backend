package org.ignite.service;

import org.ignite.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<List<?>> getListUser();

    public UserDto getUserById(int id);

    public List<List<?>> searchUser(String keyword);
}
