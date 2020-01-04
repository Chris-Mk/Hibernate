package app.service.abstraction;

import app.domain.models.service.UserDeleteServiceModel;
import app.domain.models.service.UserRegistryServiceModel;
import app.domain.models.service.UserUpdateServiceModel;
import app.domain.models.view.TubeViewModel;
import app.domain.models.view.UserViewModel;

import java.util.Collection;

public interface UserService {

    UserViewModel getUserByUsernameAndEmail(String username, String email);

    UserViewModel getUserByUsername(String username);

    UserViewModel getUserByEmail(String email);

    UserViewModel getUserById(String id);

    int removeUser(Collection<UserDeleteServiceModel> userDeleteServiceModels);

    void createUser(UserRegistryServiceModel userRegistryServiceModel);

    Collection<UserViewModel> getAllUsers();

    int updateUser(Collection<UserUpdateServiceModel> userUpdateServiceModels);

    Collection<TubeViewModel> getAllTubes(String username);

    UserViewModel getUserByUsernameAndPassword(String username, String password);
}
