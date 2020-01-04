package app.service.concretion;

import app.domain.entities.Tube;
import app.domain.entities.User;
import app.domain.models.service.UserDeleteServiceModel;
import app.domain.models.service.UserRegistryServiceModel;
import app.domain.models.service.UserUpdateServiceModel;
import app.domain.models.view.TubeViewModel;
import app.domain.models.view.UserViewModel;
import app.repository.abstraction.UserRepository;
import app.service.abstraction.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserViewModel getUserByUsernameAndEmail(String username, String email) {
        User user = this.userRepository.findByUsernameAndEmail(username, email);
        return this.modelMapper.map(user, UserViewModel.class);
    }

    @Override
    public UserViewModel getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return this.modelMapper.map(user, UserViewModel.class);
    }

    @Override
    public UserViewModel getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        return this.modelMapper.map(user, UserViewModel.class);
    }

    @Override
    public UserViewModel getUserById(String id) {
        User user = this.userRepository.findById(id);
        return this.modelMapper.map(user, UserViewModel.class);
    }

    @Override
    public int removeUser(Collection<UserDeleteServiceModel> userDeleteServiceModels) {
        Collection<User> users = this.modelMapper.map(userDeleteServiceModels, new TypeToken<Collection<User>>() {}.getType());
        return this.userRepository.delete(users);
    }

    @Override
    public void createUser(UserRegistryServiceModel userRegistryServiceModel) {
        User user = this.modelMapper.map(userRegistryServiceModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public Collection<UserViewModel> getAllUsers() {
        Collection<User> users = this.userRepository.findAll();
        return this.modelMapper.map(users, new TypeToken<Collection<UserViewModel>>() {}.getType());
    }

    @Override
    public int updateUser(Collection<UserUpdateServiceModel> userUpdateServiceModels) {
        Collection<User> users = this.modelMapper.map(userUpdateServiceModels, new TypeToken<Collection<User>>() {}.getType());
        return this.userRepository.update(users);
    }

    @Override
    public Collection<TubeViewModel> getAllTubes(String username) {
        List<Tube> allTubes = this.userRepository.findAllTubes(username);
        return this.modelMapper.map(allTubes, new TypeToken<Collection<TubeViewModel>>() {}.getType());
    }

    @Override
    public UserViewModel getUserByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        return this.modelMapper.map(user, UserViewModel.class);
    }
}
