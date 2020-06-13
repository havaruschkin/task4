package by.home.demo.service;

import by.home.demo.exception.UsernameAlreadyUsedException;
import by.home.demo.model.Authority;
import by.home.demo.model.User;
import by.home.demo.repository.AuthorityRepository;
import by.home.demo.repository.UserRepository;
import by.home.demo.security.AuthoritiesConstants;
import by.home.demo.service.dto.UserDto;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registrationUser(UserDto userDto, String password) {
        userRepository.findByLogin(userDto.getLogin().toLowerCase()).ifPresent(user -> {
            throw new UsernameAlreadyUsedException();
        });
        String encryptedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setLogin(userDto.getLogin().toLowerCase());
        newUser.setPassword(encryptedPassword);
        newUser.setName(userDto.getName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail().toLowerCase());
        newUser.setBlock(false);
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findByName(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        return newUser;
    }

//    public Optional<User> activateRegistration(@RequestParam(value = "key") String key) {
//        return userRepository.findByActivationKey(key).map(user -> {
//            user.setActivationKey(null);
//            user.setActivated(true);
//            return user;
//        });
//    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> findUser(String login) {
        return userRepository.findByLogin(login);
    }
}
