package org.ignite.service;

import org.ignite.Dao.UserRepository;
import org.ignite.Entity.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<?> getListUsers() {
        List<?> users = userRepository.getListUsers();
        return users;
    }


    public List<?> getUserById(int id) {
        List<?> user = userRepository.findUserById(id);
        return user;
    }


    public List<List<?>> searchUser(String keyword) {
//        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT user_id, username, email, phone, avatar, registered_date, admin_id FROM USER WHERE username LIKE \'%" + keyword + "%\';");
//        List<List<?>> res = userCache.query(qry.setDistributedJoins(true)).getAll();
//        if (res != null) return res;
//        throw new NotFoundException("Không tìm thấy người dùng phù hợp");
        return null;
    }

}
