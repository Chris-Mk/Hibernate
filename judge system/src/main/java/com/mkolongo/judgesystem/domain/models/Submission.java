package com.mkolongo.judgesystem.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "submissions")
public class Submission extends BaseModel {

    private User user;
    private Strategy strategy;
    private double codePerformance;
    private double points;
    private Problem problem;

    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
//    @JoinColumn(name = "strategy_id", referencedColumnName = "id")
    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Column(name = "code_performance")
    public double getCodePerformance() {
        return codePerformance;
    }

    public void setCodePerformance(double codePerformance) {
        this.codePerformance = codePerformance;
    }

    @Column
    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    @ManyToOne
//    @JoinColumn(name = "problem_id", referencedColumnName = "id")
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
