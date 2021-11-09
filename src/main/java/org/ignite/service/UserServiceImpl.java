package org.ignite.service;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.ignite.Entity.User;
import org.ignite.Entity.UserKey;
import org.ignite.Entity.eBook;
import org.ignite.Entity.eBookKey;
import org.ignite.exception.NotFoundException;
import org.ignite.model.dto.UserDto;
import org.ignite.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserServiceImpl implements  UserService{

    @Autowired
    private Ignite ignite;

    IgniteCache<UserKey, User> userCache = ignite.cache("user");



    @Override
    public List<List<?>> getListUser() {
        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT user_id, username, email, phone, avatar, registered_date, admin_id FROM USER");
        List<List<?>> res = userCache.query(qry.setDistributedJoins(true)).getAll();

        if (res != null)    return res;
        throw new NotFoundException("Chưa có User nào trong hệ thống");
    }

    @Override
    public UserDto getUserById(int id) {
        UserKey userKey = new UserKey(id, 1);
        User user = userCache.get(userKey);
        UserDto userDto = UserMapper.toUserDto(user);
        if (userDto != null) return userDto;
        throw new NotFoundException("User không tồn tại trong hệ thống");
    }

    @Override
    public List<List<?>> searchUser(String keyword) {
        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT user_id, username, email, phone, avatar, registered_date, admin_id FROM USER WHERE username LIKE \'%" + keyword + "%\';");
        List<List<?>> res = userCache.query(qry.setDistributedJoins(true)).getAll();
        if (res != null) return res;
        throw new NotFoundException("Không tìm thấy người dùng phù hợp");
    }

}
