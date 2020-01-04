package com.mkolongo.judgesystem.domain.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "contests")
public class Contest extends BaseModel {

    private String name;
    private Category category;
    private Set<Problem> problems;
    private Set<User> contestants;
    private Set<MaxResultPerContest> maxResultPerContests;
    private Set<Strategy> strategies;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "contest")
    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    @ManyToMany(mappedBy = "contests")
    public Set<User> getContestants() {
        return contestants;
    }

    public void setContestants(Set<User> contestants) {
        this.contestants = contestants;
    }

    @OneToMany(mappedBy = "contest")
    public Set<MaxResultPerContest> getMaxResultPerContests() {
        return maxResultPerContests;
    }

    public void setMaxResultPerContests(Set<MaxResultPerContest> maxResultPerContests) {
        this.maxResultPerContests = maxResultPerContests;
    }

    @ManyToMany
    @JoinTable(name = "contests_strategies",
            joinColumns = @JoinColumn(name = "contest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "strategy_id", referencedColumnName = "id"))
    public Set<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(Set<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Contest contest = (Contest) object;
        return name.equals(contest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
