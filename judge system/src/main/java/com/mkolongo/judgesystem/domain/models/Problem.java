package com.mkolongo.judgesystem.domain.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "problems")
public class Problem extends BaseModel {

    private String name;
    private Set<Submission> submissions;
    private Set<User> contestants;
    private Contest contest;
    private Set<MaxResultPerProblem> maxResultPerProblems;
    private Set<Test> tests;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "problem")
    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    @ManyToMany(mappedBy = "problems")
    public Set<User> getContestants() {
        return contestants;
    }

    public void setContestants(Set<User> contestants) {
        this.contestants = contestants;
    }

    @ManyToOne
//    @JoinColumn(name = "contest_id", referencedColumnName = "id")
    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    @OneToMany(mappedBy = "problem")
    public Set<MaxResultPerProblem> getMaxResultPerProblems() {
        return maxResultPerProblems;
    }

    public void setMaxResultPerProblems(Set<MaxResultPerProblem> maxResultPerProblems) {
        this.maxResultPerProblems = maxResultPerProblems;
    }

    @OneToMany(mappedBy = "problem")
    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Problem problem = (Problem) object;
        return name.equals(problem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
