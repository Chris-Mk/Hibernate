package entities;

import identifiers.NumberIdentifier;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends NumberIdentifier {

    private String name;
    private Country country;
    private Set<Team> teams;

    public Town() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Country.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "town", targetEntity = Team.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
