package com.mkolongo.usersystem.controller;

import com.mkolongo.usersystem.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        String emailProvider = scanner.nextLine();

        this.userService.getUsersByEmailProvider(emailProvider)
                .forEach(user -> System.out.println(String.format("%s %s %s",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail())));

        LocalDateTime lastLoginTime = LocalDateTime.parse(scanner.nextLine());

        System.out.println(this.userService.removeInactiveUsers(lastLoginTime) + "users have been deleted");
    }
}
