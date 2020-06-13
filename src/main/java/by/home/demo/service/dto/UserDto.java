package by.home.demo.service.dto;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    @NotBlank
    private String login;

    private String email;
    private String name;
    private String lastName;
    private boolean block = false;
    private Set<String> authorities;
}
