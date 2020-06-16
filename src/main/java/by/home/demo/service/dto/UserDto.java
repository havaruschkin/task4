package by.home.demo.service.dto;

import by.home.demo.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String email;

    private Status status;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdTs;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastLogin;
}
