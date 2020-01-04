package com.mkolongo.judgesystem.domain.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "users")
public class User extends BaseModel {

    private String username;
    private Set<Submission> submissions;
    private Set<MaxResultPerContest> maxResultPerContest;
    private Set<MaxResultPerProblem> maxResultPerProblem;
    private Set<Contest> contests;
    private Set<Problem> problems;

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(mappedBy = "user")
    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    @OneToMany(mappedBy = "user")
    public Set<MaxResultPerContest> getMaxResultPerContest() {
        return maxResultPerContest;
    }

    public void setMaxResultPerContest(Set<MaxResultPerContest> maxResultPerContest) {
        this.maxResultPerContest = maxResultPerContest;
    }

    @OneToMany(mappedBy = "user")
    public Set<MaxResultPerProblem> getMaxResultPerProblem() {
        return maxResultPerProblem;
    }

    public void setMaxResultPerProblem(Set<MaxResultPerProblem> maxResultPerProblem) {
        this.maxResultPerProblem = maxResultPerProblem;
    }

    @ManyToMany
    @JoinTable(name = "users_contests",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contest_id", referencedColumnName = "id"))
    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }

    @ManyToMany
    @JoinTable(name = "users_problems",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id", referencedColumnName = "id"))
    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
