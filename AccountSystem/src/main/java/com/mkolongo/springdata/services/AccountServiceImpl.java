package com.mkolongo.springdata.services;

import com.mkolongo.springdata.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mkolongo.springdata.repositories.AccountRepository;
import com.mkolongo.springdata.services.base.AccountService;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, long id) {
        checkIfAccountExist(id);
        checkIfAccountHasUser(id);

        final Account account = accountRepository.getAccountById(id);
        final BigDecimal balance = account.getBalance();

        if (balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("You are trying to Withdraw more than the Balance!");
        }
        account.setBalance(balance.subtract(amount));
        accountRepository.saveAndFlush(account);
    }

    @Override
    public void transferMoney(BigDecimal amount, long id) {
        checkIfAccountHasUser(id);

        final Account account = accountRepository.getAccountById(id);
        final BigDecimal balance = account.getBalance();

        if (balance.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Cant transfer negative amount!");
        }

        account.setBalance(balance.subtract(amount));
        accountRepository.saveAndFlush(account);
    }

    private void checkIfAccountExist(long id) {
        if (accountRepository.getAccountById(id) == null) {
            throw new NullPointerException("Account doest not Exist!");
        }
    }

    private void checkIfAccountHasUser(long id) {
        if (accountRepository.getAccountById(id).getUser() == null) {
            throw new NullPointerException("Wrong user!");
        }
    }
}
