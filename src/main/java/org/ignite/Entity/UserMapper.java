package org.ignite.Entity;



public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto tmp = new UserDto();
        tmp.setUsername(user.getUsername());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setAvatar(user.getAvatar());
        tmp.setRegisteredDate(user.getRegisteredDate());
        return tmp;
    }


}
