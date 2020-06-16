package by.home.demo.service.mapper;

import by.home.demo.model.User;
import by.home.demo.service.dto.UserDto;
import java.util.List;

public interface UserMapper {

    List<UserDto> toUserDTOs(List<User> users);

    UserDto toUserDto(User user);
}
