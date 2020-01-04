package entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BetGameId implements Serializable {

    private Game game;
    private Bet bet;

    public BetGameId() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBets() {
        return bet;
    }

    public void setBets(Bet bet) {
        this.bet = bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetGameId betGameId = (BetGameId) o;
        return game.equals(betGameId.game) &&
                bet.equals(betGameId.bet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, bet);
    }
}
