package by.home.demo.service.mapper.impl;

import by.home.demo.model.Authority;
import by.home.demo.model.User;
import by.home.demo.service.dto.UserDto;
import by.home.demo.service.mapper.UserMapper;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setBlock(user.isBlock());
        userDto.setAuthorities(user.getAuthorities()
                .stream()
                .map(Authority::getName)
                .collect(Collectors.toSet()));
        return userDto;
    }

    public User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDto.getId());
            user.setLogin(userDto.getLogin());
            user.setName(userDto.getName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setBlock(userDto.isBlock());
            Set<Authority> authorities = this.authoritiesFromStrings(userDto.getAuthorities());
            user.setAuthorities(authorities);
            return user;
        }
    }

    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();
        if (authoritiesAsString != null) {
            authorities = authoritiesAsString.stream().map(string -> {
                Authority auth = new Authority();
                auth.setName(string);
                return auth;
            }).collect(Collectors.toSet());
        }
        return authorities;
    }
}
