package entities;

import identifiers.NumberIdentifier;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends NumberIdentifier {

    private String name;
    private byte[] logo;
    private String initial;
    private Color primaryKitColor;
    private Color secondaryKitColor;
    private Town town;
    private BigDecimal budget;
    private Set<Player> players;
    private Game game;

    public Team() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Column(length = 3)
    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    @OneToOne(targetEntity = Color.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_kit_color_id", referencedColumnName = "id")
    public Color getPrimaryKitColor() {
        return primaryKitColor;
    }

    public void setPrimaryKitColor(Color primaryKitColor) {
        this.primaryKitColor = primaryKitColor;
    }

    @OneToOne(targetEntity = Color.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "secondary_kit_color_id", referencedColumnName = "id")
    public Color getSecondaryKitColor() {
        return secondaryKitColor;
    }

    public void setSecondaryKitColor(Color secondaryKitColor) {
        this.secondaryKitColor = secondaryKitColor;
    }

    @ManyToOne(targetEntity = Town.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Column(precision = 8, scale = 2)
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @OneToMany(mappedBy = "team", targetEntity = Player.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @OneToOne(mappedBy = "homeTeam", targetEntity = Game.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
