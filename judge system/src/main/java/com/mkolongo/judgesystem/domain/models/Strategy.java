package com.mkolongo.judgesystem.domain.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "strategies")
public class Strategy extends BaseModel {

    private String name;
    private Set<Contest> contests;

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "strategies")
    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Strategy strategy = (Strategy) object;
        return name.equals(strategy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
