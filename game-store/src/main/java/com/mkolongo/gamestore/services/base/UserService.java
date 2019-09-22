package com.mkolongo.gamestore.services.base;

import com.mkolongo.gamestore.domain.models.User;
import com.mkolongo.gamestore.dtos.UserLoginUserDto;
import com.mkolongo.gamestore.dtos.UserRegisterUserDto;
import com.mkolongo.gamestore.dtos.views.OwnedGamesDto;

import java.util.List;

public interface UserService {

    String registerUser(UserRegisterUserDto userRegisterDto);

    String loginUser(UserLoginUserDto userLoginDto);

    String logoutUser();

    String getLoggedInUserEmail();

    User getUserByEmail(String email);

    String buyGame(long id);

    List<OwnedGamesDto> getGamesOwnedByLoggedInUser();
}
