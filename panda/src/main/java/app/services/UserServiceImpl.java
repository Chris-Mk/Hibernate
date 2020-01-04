package app.services;

import app.domain.entities.Role;
import app.domain.entities.User;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.view.UserViewModel;
import app.repositories.UserRepository;
import app.util.PasswordEncryption;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserViewModel> getAllUsers() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserViewModel>>() {}.getType());
    }

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User user = modelMapper.map(userRegisterBindingModel, User.class);
        user.setRole(userRepository.count() == 0 ? Role.Admin : Role.User);

        String encrypt = PasswordEncryption.encrypt(userRegisterBindingModel.getPassword());
        user.setPassword(encrypt);

        userRepository.save(user);
    }

    @Override
    public UserViewModel getUserByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserViewModel.class);
    }

}
