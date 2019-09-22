package com.mkolongo.gamestore.dtos.base;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public abstract class BaseUserDto {
    private String email;
    private String password;

    protected BaseUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @NotNull(message = "Email cannot be null")
    @Email(regexp = ".*@.*\\..*", message = "Incorrect email.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?!.*[&%$]).*$", message = "Password must contain at least 1 uppercase, 1 lowercase letter and 1 digit.")
    @Length(min = 6, message = "Password must be at least 6 symbols long.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
