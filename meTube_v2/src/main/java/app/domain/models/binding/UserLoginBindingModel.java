package app.domain.models.binding;

import javax.validation.constraints.NotEmpty;

public class UserLoginBindingModel {
    private String username;
    private char[] password;

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
}
