package entities;

import identifiers.NumberIdentifier;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bets")
public class Bet extends NumberIdentifier {

    private BigDecimal betMoney;
    private LocalDateTime date;
    private User user;
    private Set<BetGame> betGames;

    public Bet() {
    }

    @Column(name = "bet_money")
    public BigDecimal getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(BigDecimal betMoney) {
        this.betMoney = betMoney;
    }

    @Column(name = "date_of_bet")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(targetEntity = BetGame.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bets_and_bets_games",
    joinColumns = @JoinColumn(name = "bet_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "bet_game_id", referencedColumnName = "id"))
    public Set<BetGame> getBetGames() {
        return betGames;
    }

    public void setBetGames(Set<BetGame> betGames) {
        this.betGames = betGames;
    }
}
