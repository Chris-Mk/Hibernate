package com.mkolongo.judgesystem.domain.dtos;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SubmissionImportDto {
    private long id;

    @XmlElement(name = "performance")
    @NotNull(message = "Code performance cannot be empty!")
    @Digits(integer = 5, fraction = 3, message = "Code performance must have 3 digits of precision!")
    private double codePerformance;

    @XmlElement(name = "user")
    @NotNull(message = "User cannot be empty!")
    private UserIdDto userId;

    @XmlElement(name = "problem")
    @NotNull(message = "Problem cannot be empty!")
    private ProblemIdtDto problemId;

    @NotNull(message = "Points cannot be empty!")
    private double points;

    @XmlElement(name = "strategy")
    @NotNull(message = "Strategy cannot be empty!")
    private StrategyIdDto strategyId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCodePerformance() {
        return codePerformance;
    }

    public void setCodePerformance(double codePerformance) {
        this.codePerformance = codePerformance;
    }

    public UserIdDto getUserId() {
        return userId;
    }

    public void setUserId(UserIdDto userId) {
        this.userId = userId;
    }

    public ProblemIdtDto getProblemId() {
        return problemId;
    }

    public void setProblemId(ProblemIdtDto problemId) {
        this.problemId = problemId;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public StrategyIdDto getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(StrategyIdDto strategyId) {
        this.strategyId = strategyId;
    }
}
