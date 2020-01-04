package entities;

import identifiers.NumberIdentifier;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player extends NumberIdentifier {

    private String name;
    private int squadNumber;
    private Team team;
    private Position position;
    private boolean isCurrentlyInjured;

    public Player() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "squad_number")
    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }

    @ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @ManyToOne(targetEntity = Position.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Column(name = "is_currently_injured")
    public boolean isCurrentlyInjured() {
        return isCurrentlyInjured;
    }

    public void setCurrentlyInjured(boolean currentlyInjured) {
        isCurrentlyInjured = currentlyInjured;
    }
}
