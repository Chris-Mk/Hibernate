package com.mkolongo.residentEvil.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRegisterBindingModel {

    @NotBlank(message = "Username required!")
    private String username;

    @NotBlank(message = "Password required!")
    private String password;

    @NotBlank(message = "Confirm Password required!")
    private String confirmPassword;

    @NotBlank(message = "Email required!")
    @Email(regexp = "^\\w+[.\\w]*@\\w+[.\\w]*", message = "Invalid email!")
    private String email;
}
