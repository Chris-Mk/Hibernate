package com.mkolongo.gamestore.dtos;

import com.mkolongo.gamestore.dtos.base.BaseUserDto;

import javax.validation.constraints.NotNull;

public class UserRegisterUserDto extends BaseUserDto {
    private String confirmPassword;
    private String fullName;

    public UserRegisterUserDto(String email, String password, String confirmPassword, String fullName) {
        super(email, password);
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    @NotNull(message = "Confirm Password cannot be null")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
