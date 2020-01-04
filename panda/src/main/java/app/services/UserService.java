package app.services;

import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.view.UserViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface UserService {

    void registerUser(UserRegisterBindingModel userRegisterBindingModel) throws InvalidKeySpecException, NoSuchAlgorithmException;

    UserViewModel getUserByUsername(String username);

    List<UserViewModel> getAllUsers();
}
