package by.home.demo.service.vm;

import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class LoginVM {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
