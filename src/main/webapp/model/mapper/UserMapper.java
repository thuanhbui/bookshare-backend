package model.mapper;

import model.Entity.User;
import model.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto tmp = new UserDto();
        tmp.setUsername(user.getUsername());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setAddress(user.getAddress());
        tmp.setRegistered_date(user.getRegisteredDate());
        
        return tmp;
    }
}
