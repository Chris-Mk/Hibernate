package com.mkolongo.springdata.models;

import com.mkolongo.springdata.models.base.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account extends BaseModel {

    private BigDecimal balance;
    private User user;

    public Account() {
    }

    @Column
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        if (balance.compareTo(new BigDecimal(0)) > 0) {
            this.balance = balance;
        }
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
