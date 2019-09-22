package com.mkolongo.gamestore.domain.models;

import com.mkolongo.gamestore.domain.models.base.BaseModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseModel {

    private String email;
    private String password;
    private String fullName;
//    private List<Game> games;
    private boolean isAdmin;
    private List<Order> orders;

    public User() {
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

//    @ManyToMany
//    @JoinTable(name = "users_games",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
//    public List<Game> getGames() {
//        return games;
//    }
//
//    public void setGames(List<Game> games) {
//        this.games = games;
//    }

    @Column(name = "is_admin")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
