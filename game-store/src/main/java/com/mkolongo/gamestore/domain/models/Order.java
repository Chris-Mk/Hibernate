package com.mkolongo.gamestore.domain.models;

import com.mkolongo.gamestore.domain.models.base.BaseModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseModel {

    private User user;
    private List<Game> games;

    public Order() {
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
