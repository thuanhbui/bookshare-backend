package Service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.dto.UserDto;

@Service
public interface UserService {
    public List<UserDto> getListUser();
}
