package by.home.demo.controller;

import by.home.demo.exception.InvalidPasswordException;
import by.home.demo.service.UserService;
import by.home.demo.service.dto.UserDto;
import by.home.demo.service.mapper.UserMapper;
import by.home.demo.service.vm.UserVM;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountResourceController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AccountResourceController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@RequestBody UserVM userVM) {
        if (!checkPasswordLength(userVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        userService.registrationUser(userVM, userVM.getPassword());
    }

    @PostMapping("/users/lock")
    public void lock(@RequestBody List<Long> userIds) {
        userService.lockUsers(userIds);
    }

    @PostMapping("/users/unlock")
    public void unlock(@RequestBody List<Long> userIds) {
        userService.unlockUsers(userIds);
    }

    @PostMapping("/users/delete")
    public void delete(@RequestBody List<Long> userIds) {
        userService.deleteUsers(userIds);
    }

    @GetMapping("/users")
    public List<UserDto> allUsers() {
        return userMapper.toUserDTOs(userService.findAllUsers());
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= UserVM.PASSWORD_MIN_LENGTH;
    }
}
