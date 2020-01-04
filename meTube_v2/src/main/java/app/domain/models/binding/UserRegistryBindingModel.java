package app.domain.models.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRegistryBindingModel {
    private String username;
    private char[] password;
    private char[] confirmPassword;
    private String email;

    @NotEmpty(message = "Username required!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "Password required!")
    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @NotEmpty(message = "Confirm Password required!")
    public char[] getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(char[] confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotEmpty(message = "Email required!")
    @Email(regexp = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$", message = "Invalid email!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
