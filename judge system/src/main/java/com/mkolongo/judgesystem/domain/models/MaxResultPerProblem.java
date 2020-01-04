package com.mkolongo.judgesystem.domain.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity(name = "max_result_for_problem")
public class MaxResultPerProblem extends BaseModel {

    private User user;
    private Problem problem;
    private Submission submission;

    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
//    @JoinColumn(name = "problem_id", referencedColumnName = "id")
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @ManyToOne
//    @JoinColumn(name = "submission_id", referencedColumnName = "id")
    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MaxResultPerProblem that = (MaxResultPerProblem) object;
        return user.equals(that.user) &&
                problem.equals(that.problem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, problem);
    }
}
