package app.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterBindingModel {

    @NotEmpty(message = "Username is required!")
    @Size(min = 2, max = 20, message = "Invalid username!")
    private String username;

    @NotEmpty(message = "Password is required!")
    private String password;

    @NotEmpty(message = "Confirm password is required!")
    private String confirmPassword;

    private String email;
}
