package com.mkolongo.judgesystem.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tests")
public class Test extends BaseModel {

    private String expectedResult;
    private String testContent;
    private Problem problem;

    @Column(name = "expected_result")
    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Column(name = "test_content")
    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
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
