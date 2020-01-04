package entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bet_games")
public class BetGame {

    @EmbeddedId
    private BetGameId betGameId;

    @NaturalId
    private Set<Game> games;
    private Set<Bet> bets;
    private Set<ResultPrediction> resultPredictions;

    public BetGame() {
    }

    public BetGameId getBetGameId() {
        return betGameId;
    }

    public void setBetGameId(BetGameId betGameId) {
        this.betGameId = betGameId;
    }

    @ManyToMany(mappedBy = "betGames", targetEntity = Game.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @ManyToMany(mappedBy = "betGames", targetEntity = Bet.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Bet> getBets() {
        return bets;
    }

    public void setBets(Set<Bet> bets) {
        this.bets = bets;
    }

    @ManyToMany(targetEntity = ResultPrediction.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "bet_game_result_predictions",
    joinColumns = @JoinColumn(name = "bet_game_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "result_prediction_id", referencedColumnName = "id"))
    public Set<ResultPrediction> getResultPredictions() {
        return resultPredictions;
    }

    public void setResultPredictions(Set<ResultPrediction> resultPredictions) {
        this.resultPredictions = resultPredictions;
    }
}
