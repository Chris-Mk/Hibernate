package com.mkolongo.springdata.controller;

import com.mkolongo.springdata.models.Account;
import com.mkolongo.springdata.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.mkolongo.springdata.services.AccountServiceImpl;
import com.mkolongo.springdata.services.UserServiceImpl;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) {
        User user = new User();
        user.setUsername("Gosho");
        user.setAge(30);

        Account account = new Account();
        account.setBalance(new BigDecimal(25000));
        account.setUser(user);

        user.setAccounts(Set.of(account));
        userService.registerUser(user);

        accountService.withdrawMoney(new BigDecimal(20000), account.getId());
        //accountService.transferMoney(new BigDecimal(20000), 1L);
    }
}
