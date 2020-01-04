package entities;

import identifiers.StringIdentifier;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Country extends StringIdentifier {

    private String name;
    private Continent continent;
    private Set<Town> towns;

    public Country() {
    }

    @Id
    @Column(length = 3)
    @Override
    public String getId() {
        return super.getId();
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Continent.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id", referencedColumnName = "id")
    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @OneToMany(mappedBy = "country", targetEntity = Town.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
