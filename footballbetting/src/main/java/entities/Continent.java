package entities;

import identifiers.NumberIdentifier;
import jdk.jfr.Enabled;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent extends NumberIdentifier {

    private String name;
    private Set<Country> countries;

    public Continent() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "continent", targetEntity = Country.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}
