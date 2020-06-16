package by.home.demo.service;

import by.home.demo.exception.UsernameAlreadyUsedException;
import by.home.demo.model.Status;
import by.home.demo.model.User;
import by.home.demo.repository.UserRepository;
import by.home.demo.service.dto.UserDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrationUser(UserDto userDto, String password) {
        userRepository.findByEmail(userDto.getEmail().toLowerCase()).ifPresent(user -> {
            throw new UsernameAlreadyUsedException();
        });
        String encryptedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setLogin(userDto.getLogin().toLowerCase());
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(userDto.getEmail().toLowerCase());
        newUser.setStatus(Status.ACTIVE);
        userRepository.save(newUser);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByLogin(username);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void lockUsers(List<Long> userIds) {
        for (Long id : userIds) {
            findUser(id).ifPresent(user -> user.setStatus(Status.BLOCKED));
        }
    }

    public void unlockUsers(List<Long> userIds) {
        for (Long id : userIds) {
            findUser(id).ifPresent(user -> user.setStatus(Status.ACTIVE));
        }
    }

    public void deleteUsers(List<Long> userIds) {
        for (Long id : userIds) {
            deleteUser(id);
        }
    }

    public void updateLastLoginUser(String username) {
        userRepository.findByLogin(username).ifPresent(user -> {
            user.setLastLogin(LocalDateTime.now());
        });
    }
}
