package by.home.demo.controller;

import by.home.demo.exception.InvalidPasswordException;
import by.home.demo.model.User;
import by.home.demo.repository.UserRepository;
import by.home.demo.service.UserService;
import by.home.demo.service.vm.UserVM;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountResourceController {

    private final UserRepository userRepository;
    private final UserService userService;

    public AccountResourceController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registeAccount(@RequestBody UserVM userVM) {
        if (!checkPasswordLength(userVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        User user = userService.registrationUser(userVM, userVM.getPassword());
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= UserVM.PASSWORD_MIN_LENGTH;
    }
}
