package app.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserLoginBindingModel {

    @NotEmpty(message = "Username is required!")
    private String username;

    @NotEmpty(message = "Password is required!")
    private String password;
}
