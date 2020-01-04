package com.mkolongo.springdata.repositories;

import com.mkolongo.springdata.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getAccountById(long id);
}
