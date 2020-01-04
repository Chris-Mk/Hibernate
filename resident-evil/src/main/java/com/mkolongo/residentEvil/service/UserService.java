package com.mkolongo.residentEvil.service;

import com.mkolongo.residentEvil.domain.models.service.UserServiceModel;
import com.mkolongo.residentEvil.domain.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel register(UserServiceModel userServiceModel);

    List<UserViewModel> getAllUsers();

    UserViewModel getUserById(Long id);

    void editRoles(Long id, boolean moderator, boolean admin);
}
