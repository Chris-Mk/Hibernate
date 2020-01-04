package entities;

import identifiers.NumberIdentifier;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rounds")
public class Round extends NumberIdentifier {

    private String name;
    private Set<Game> games;

    public Round() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Game.class)
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
