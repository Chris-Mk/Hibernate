package entities;

import identifiers.NumberIdentifier;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competition_types")
public class CompetitionType extends NumberIdentifier {

    private String name;
    private Set<Competition> competitions;

    public CompetitionType() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "competitionType",cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Competition.class)
    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }
}
