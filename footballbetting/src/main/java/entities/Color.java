package entities;

import identifiers.NumberIdentifier;

import javax.persistence.*;
import java.lang.annotation.Repeatable;
import java.util.Set;

@Entity
@Table(name = "colors")
public class Color extends NumberIdentifier {

    private String name;
    private Team team;

    public Color() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(targetEntity = Team.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "primaryKitColor")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
