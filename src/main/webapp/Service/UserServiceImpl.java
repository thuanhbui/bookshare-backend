package Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import model.Entity.User;
import model.dto.UserDto;
import model.mapper.UserMapper;

@Component
public class UserServiceImpl implements UserService{
    private static ArrayList<User> users = new ArrayList<User>();

    static {
        users.add(new User("eBookshare", "123456", "19020281@vnu.edu.vn", "1234567890", "abcd", null));
        users.add(new User("Bùi Như Lạc", "5432", "laclac@gmail.com","0123456789","avatar1.img", null));
    }


    @Override
    public List<UserDto> getListUser() {
        System.out.println("getLi");

        List<UserDto> result = new ArrayList<>();
        for(User user: users) {
            result.add(UserMapper.toUserDto(user));
        }

        return result;
    }

    
}
