package by.home.demo.service.vm;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
public class LoginVM {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
