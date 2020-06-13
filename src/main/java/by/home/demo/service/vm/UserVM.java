package by.home.demo.service.vm;

import by.home.demo.service.dto.UserDto;
import javax.validation.constraints.Size;

public class UserVM extends UserDto {

    public static final int PASSWORD_MIN_LENGTH = 1;

    @Size(min = PASSWORD_MIN_LENGTH)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
