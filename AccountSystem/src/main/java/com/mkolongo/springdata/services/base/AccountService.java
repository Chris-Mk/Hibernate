package com.mkolongo.springdata.services.base;

import java.math.BigDecimal;

public interface AccountService {

    void withdrawMoney(BigDecimal amount, long id);

    void transferMoney(BigDecimal amount, long id);
}
