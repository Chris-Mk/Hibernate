package entities;

import helpers.Prediction;
import identifiers.NumberIdentifier;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends NumberIdentifier {

    private Prediction prediction;
    private Set<BetGame> betGames;

    public ResultPrediction() {
    }

    @Enumerated(value = EnumType.STRING)
    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }

    @ManyToMany(mappedBy = "resultPredictions", targetEntity = BetGame.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<BetGame> getBetGames() {
        return Collections.unmodifiableSet(betGames);
    }

    public void setBetGames(Set<BetGame> betGames) {
        this.betGames = betGames;
    }
}
