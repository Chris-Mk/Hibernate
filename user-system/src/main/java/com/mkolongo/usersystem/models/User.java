package com.mkolongo.usersystem.models;

import com.mkolongo.usersystem.helpers.Password;
import com.mkolongo.usersystem.models.base.BaseModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseModel {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private LocalDateTime registeredOn;
    private LocalDateTime lastTimeLoggedIn;
    private int age;
    private boolean isDeleted;
    private Set<User> friends;
    private Town homeTown;
    private Town currentTown;

    public User() {
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    @NotNull
    @Size(min = 4, max = 30, message = "First name must be between 4 and 30 length")
    @Column(name = "first_name", length = 30, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Size(min = 4, max = 30, message = "Last name must be between 4 and 30 length")
    @Column(name = "last_name", length = 30, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 length")
    @Column(nullable = false, length = 50)
    @Pattern(regexp = "")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(regexp = "^([a-zA-Z0-9]+)([._-]+)?[a-zA-Z0-9]+@([a-zA-Z]+[-]?[a-zA-Z]+\\.)+[a-zA-Z]+[-]?[a-zA-Z]+")
    @Column(nullable = false)
    @Password(minLength = 6,
            maxLength = 50,
            containsDigits = true,
            containsLowercase = true,
            containsUppercase = true,
            containsSpecialSymbols = true,
            message = "Invalid Password!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "registered_on")
    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Column(name = "last_time_logged_in")
    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    @Column
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setFullName(String fullName) {
    }

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_town_id")
    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_town_id")
    public Town getCurrentTown() {
        return currentTown;
    }

    public void setCurrentTown(Town currentTown) {
        this.currentTown = currentTown;
    }
}
