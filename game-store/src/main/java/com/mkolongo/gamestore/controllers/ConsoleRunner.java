package com.mkolongo.gamestore.controllers;

import com.mkolongo.gamestore.dtos.*;
import com.mkolongo.gamestore.services.base.GameService;
import com.mkolongo.gamestore.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;
    private final Scanner scanner;

    @Autowired
    public ConsoleRunner(UserService userService, GameService gameService, Scanner scanner) {
        this.userService = userService;
        this.gameService = gameService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) {
        while (true) {
            String input = this.scanner.nextLine();

            if (input.isEmpty()) break;

            String[] data = input.split("\\|");

            switch (data[0]) {
                case "RegisterUser":
                    UserRegisterUserDto userRegisterDto =
                            new UserRegisterUserDto(data[1], data[2], data[3], data[4]);

                    System.out.println(userService.registerUser(userRegisterDto));
                    break;
                case "LoginUser":
                    UserLoginUserDto userLoginDto = new UserLoginUserDto(data[1], data[2]);

                    System.out.println(userService.loginUser(userLoginDto));
                    break;
                case "Logout":
                    System.out.println(userService.logoutUser());
                    break;
                case "AddGame":
                    GameAddDto gameAddDto = new GameAddDto(data[1],
                            new BigDecimal(data[2]),
                            Double.parseDouble(data[3]),
                            data[4],
                            data[5],
                            data[6],
                            LocalDate.parse(data[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                    System.out.println(gameService.addGame(gameAddDto));
                    break;
                case "EditGame":
                    GameEditDto gameEditDto = new GameEditDto(Long.parseLong(data[1]), data);

                    System.out.println(gameService.editGame(gameEditDto));
                    break;
                case "DeleteGame":
                    GameDeleteDto gameDeleteDto = new GameDeleteDto(Long.parseLong(data[1]));

                    System.out.println(gameService.deleteGame(gameDeleteDto));
                    break;
                case "AllGames":
                    try {
                        gameService.getAllGames().forEach(System.out::println);
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "DetailGame":
                    try {
                        System.out.println(gameService.getGameDetailsByName(data[1]));
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "BuyGame":
                    System.out.println(userService.buyGame(Long.parseLong(data[2])));
                    break;
                case "OwnedGames":
                    userService.getGamesOwnedByLoggedInUser()
                            .forEach(System.out::println);
                    break;
            }
        }
    }
}

//AddGame|Assasin Greed 2|750.99|92.5|FqnKB22pOC0|https://us.battle.net/forums/static/images/social-thumbs/overwatch.png|Overwatch is a team-based multiplayer online first-person shooter video game developed and published by Blizzard Entertainment.|24-05-2016
//AddGame|Overwatch2|200.00|555.5|FqnKB22pOC0|https://us.battle.net/forums/static/images/social-thumbs/overwatch.png|Overwatch is a team-based multiplayer online first-person shooter video game developed and published by Blizzard Entertainment.|24-05-2016
//AddGame|Assasin Greed|1430.99|12.5|FqnKB22pOC0|https://us.battle.net/forums/static/images/social-thumbs/overwatch.png|Overwatch is a team-based multiplayer online first-person shooter video game developed and published by Blizzard Entertainment.|24-05-2016
//AddGame|Assasin Greed 2|750.99|92.5|FqnKB22pOC0|https://us.battle.net/forums/static/images/social-thumbs/overwatch.png|Overwatch is a team-based multiplayer online first-person shooter video game developed and published by Blizzard Entertainment.|24-05-2016
