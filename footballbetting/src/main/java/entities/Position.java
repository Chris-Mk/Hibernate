package entities;

import identifiers.StringIdentifier;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "positions")
public class Position extends StringIdentifier {

    private String description;
    private Set<Player> players;

    public Position() {
    }

    @Id
    @Column(length = 2)
    @Override
    public String getId() {
        return super.getId();
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "position", targetEntity = Player.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
