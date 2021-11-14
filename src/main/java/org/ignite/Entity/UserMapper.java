package org.ignite.Entity;

import org.ignite.Entity.User;
import org.ignite.Entity.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto tmp = new UserDto();
        tmp.setName(user.getUsername());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setAvatar(user.getAvatar());
        tmp.setRegistered_date(user.getRegistered_date());
        return tmp;
    }


}
