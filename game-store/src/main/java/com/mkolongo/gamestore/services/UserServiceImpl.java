package com.mkolongo.gamestore.services;

import com.mkolongo.gamestore.domain.models.Game;
import com.mkolongo.gamestore.domain.models.Order;
import com.mkolongo.gamestore.domain.models.User;
import com.mkolongo.gamestore.dtos.UserLoginUserDto;
import com.mkolongo.gamestore.dtos.UserRegisterUserDto;
import com.mkolongo.gamestore.dtos.views.OwnedGamesDto;
import com.mkolongo.gamestore.repositories.GameRepository;
import com.mkolongo.gamestore.repositories.UserRepository;
import com.mkolongo.gamestore.services.base.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final Validator validator;
    private final ModelMapper mapper;
    private String loggedInUserName;
    private String loggedInUserEmail;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository, Validator validator, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public String registerUser(UserRegisterUserDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return "Passwords dont match!";
        }

        final Set<ConstraintViolation<UserRegisterUserDto>> violations = validator.validate(userRegisterDto);

        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            for (ConstraintViolation<UserRegisterUserDto> violation : violations) {
                builder.append(violation.getMessage()).append(System.lineSeparator());
            }
            return builder.toString().trim();
        }

        User user = userRepository.findUserByEmail(userRegisterDto.getEmail());

        if (user != null) {
            return "User already exist!";
        }
        user = mapper.map(userRegisterDto, User.class);

        if (this.userRepository.count() == 0) user.setAdmin(true);

        this.userRepository.saveAndFlush(user);

        return userRegisterDto.getFullName() + " was registered";
    }

    @Override
    public String loginUser(UserLoginUserDto userLoginDto) {
        if (loggedInUserEmail != null) {
            return "Session taken!";
        }

        final User user = userRepository.findUserByEmail(userLoginDto.getEmail());

        if (user == null) {
            return "Not a registered user!";
        } else if (!user.getEmail().equals(userLoginDto.getEmail()) || !user.getPassword().equals(userLoginDto.getPassword())) {
            return "Incorrect username / password";
        }

        loggedInUserName = user.getFullName();
        loggedInUserEmail = user.getEmail();

        return "Successfully logged in " + loggedInUserName;
    }

    @Override
    public String logoutUser() {
        if (loggedInUserEmail == null) {
            return "Cannot log out. No user was logged in.";
        }

        final String result = String.format("User %s successfully logged out", loggedInUserName);

        loggedInUserName = null;
        loggedInUserEmail = null;

        return result;
    }

    @Override
    public String getLoggedInUserEmail() {
        return loggedInUserEmail;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public String buyGame(long id) {
        if (loggedInUserEmail == null) return "Cannot buy game. No user was logged in.";

        final Game game = gameRepository.findById(id).orElse(null);
        if (game == null) return "Game doest not exist!";

        final User user = userRepository.findUserByEmail(loggedInUserEmail);
//        user.getGames().add(game);

        return user.getFullName() + " successfully bought " + game.getTitle();
    }

    @Override
    @Transactional
    public List<OwnedGamesDto> getGamesOwnedByLoggedInUser() {
        if (loggedInUserEmail == null) {
            throw new IllegalArgumentException("Cannot load games. No user was logged in.");
        }

        List<OwnedGamesDto> ownedGames = new ArrayList<>();

        userRepository.findUserByEmail(loggedInUserEmail)
                .getOrders()
                .forEach(order -> {
                    final List<OwnedGamesDto> game = mapper
                            .map(order.getGames(), new TypeToken<List<OwnedGamesDto>>() {}.getType());

                    ownedGames.addAll(game);
                });

        return ownedGames;
    }
}
