package com.mkolongo.judgesystem.domain.models;

import javax.persistence.*;

@Entity(name = "max_result_for_contest")
public class MaxResultPerContest extends BaseModel {

    private User user;
    private Contest contest;
    private double averagePerformance;
    private double pointsSum;

    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
//    @JoinColumn(name = "contest_id", referencedColumnName = "id")
    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    @Column(name = "average_performance")
    public double getAveragePerformance() {
        return averagePerformance;
    }

    public void setAveragePerformance(double averagePerformance) {
        this.averagePerformance = averagePerformance;
    }

    @Column(name = "points_sum")
    public double getPointsSum() {
        return pointsSum;
    }

    public void setPointsSum(double pointsSum) {
        this.pointsSum = pointsSum;
    }
}
