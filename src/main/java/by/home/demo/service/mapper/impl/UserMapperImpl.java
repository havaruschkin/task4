package by.home.demo.service.mapper.impl;

import by.home.demo.model.User;
import by.home.demo.service.dto.UserDto;
import by.home.demo.service.mapper.UserMapper;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    public List<UserDto> toUserDTOs(List<User> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setEmail(user.getEmail());
        userDto.setStatus(user.getStatus());
        userDto.setCreatedTs(user.getCreatedTs());
        userDto.setLastLogin(user.getLastLogin());
        return userDto;
    }

    public User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDto.getId());
            user.setLogin(userDto.getLogin());
            user.setEmail(userDto.getEmail());
            user.setStatus(userDto.getStatus());
            user.setCreatedTs(userDto.getCreatedTs());
            user.setLastLogin(userDto.getLastLogin());
            return user;
        }
    }
}
