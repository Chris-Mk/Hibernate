package com.mkolongo.gamestore.services;

import com.mkolongo.gamestore.domain.models.User;
import com.mkolongo.gamestore.dtos.GameAddDto;
import com.mkolongo.gamestore.domain.models.Game;
import com.mkolongo.gamestore.dtos.GameDeleteDto;
import com.mkolongo.gamestore.dtos.GameEditDto;
import com.mkolongo.gamestore.dtos.views.AllGamesDto;
import com.mkolongo.gamestore.dtos.views.DetailsGameDto;
import com.mkolongo.gamestore.repositories.GameRepository;
import com.mkolongo.gamestore.services.base.GameService;
import com.mkolongo.gamestore.services.base.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final Validator validator;
    private final ModelMapper mapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, Validator validator, ModelMapper mapper) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public String addGame(GameAddDto gameAddDto) {
        final Set<ConstraintViolation<GameAddDto>> violations = validator.validate(gameAddDto);

        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            for (ConstraintViolation<GameAddDto> violation : violations) {
                builder.append(violation.getMessage()).append(System.lineSeparator());
            }
            return builder.toString().trim();
        }

        Game game = mapper.map(gameAddDto, Game.class);

        final Game gameByTitle = gameRepository.findGameByTitle(game.getTitle());
        if (gameByTitle != null) return "Game already exist!";

        final User user = userService.getUserByEmail(userService.getLoggedInUserEmail());

        final String validationResult = validateUser(user, "add");
        if (validationResult != null) return validationResult;

        gameRepository.saveAndFlush(game);

        return "Added " + gameAddDto.getTitle();
    }

    @Override
    public String editGame(GameEditDto gameEditDto) {
        final User user = userService.getUserByEmail(userService.getLoggedInUserEmail());

        final String validationResult = validateUser(user, "edit");
        if (validationResult != null) return validationResult;

        final Game game = gameRepository.findById(gameEditDto.getId()).orElse(null);
        if (game == null) return "Game does not exist!";

        Arrays.stream(gameEditDto.getParams())
                .skip(2)
                .forEach(value -> {
                    String[] info = value.split("=");

                    switch (info[0]) {
                        case "price":
                            game.setPrice(new BigDecimal(info[1]));
                            break;
                        case "size":
                            game.setSize(Double.parseDouble(info[1]));
                            break;
                        case "title":
                            game.setTitle(info[1]);
                            break;
                        case "trailer":
                            game.setTrailer(info[1]);
                            break;
                        case "thumbnailURL":
                            game.setImageThumbnail(info[1]);
                            break;
                        case "description":
                            game.setDescription(info[1]);
                            break;
                        case "releaseDate":
                            game.setReleaseDate(LocalDate.parse(info[1], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                            break;
                    }
                });

        this.gameRepository.saveAndFlush(game);
        return "Edited " + game.getTitle();
    }

    @Override
    public String deleteGame(GameDeleteDto gameDeleteDto) {
        final User user = userService.getUserByEmail(userService.getLoggedInUserEmail());

        final String validationResult = validateUser(user, "delete");
        if (validationResult != null) return validationResult;

        final Game game = gameRepository.findById(gameDeleteDto.getId()).orElse(null);
        if (game == null) return "Game does not exist!";

        gameRepository.delete(game);
        return "Deleted " + game.getTitle();
    }

    @Override
    public List<AllGamesDto> getAllGames() {
        if (isUserLoggedIn()) {
            throw new IllegalArgumentException("Cannot view games. No user was logged in.");
        }

        return this.gameRepository.findAll()
                .stream()
                .map(game -> mapper.map(game, AllGamesDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DetailsGameDto getGameDetailsByName(String name) {
        if (isUserLoggedIn()) {
            throw new IllegalArgumentException("Cannot view games. No user was logged in.");
        }

        final Game game = gameRepository.findGameByTitle(name);
        return mapper.map(game, DetailsGameDto.class);
    }

    private boolean isUserLoggedIn() {
        final User user = userService.getUserByEmail(userService.getLoggedInUserEmail());

        return user == null;
    }

    private String validateUser(User user, String action) {
        if (user == null) {
            return "No logged in user!";
        } else if (!user.isAdmin()) {
            return "Cannot " + action + " game. Only admins can " + action + " games!";
        }
        return null;
    }
}
