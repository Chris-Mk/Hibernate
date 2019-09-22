package com.mkolongo.gamestore.dtos;

import com.mkolongo.gamestore.dtos.base.BaseUserDto;

public class UserLoginUserDto extends BaseUserDto {

    public UserLoginUserDto(String email, String password) {
        super(email, password);
    }
}
