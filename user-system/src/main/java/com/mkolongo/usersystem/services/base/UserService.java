package com.mkolongo.usersystem.services.base;

import com.mkolongo.usersystem.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    List<User> getUsersByEmailProvider(String emailProvider);

    int removeInactiveUsers(LocalDateTime lastLoginTime);
}
