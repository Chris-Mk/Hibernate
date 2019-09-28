package app.ccb.domain.dtos;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class EmployeeImportDto {

    @SerializedName(value = "full_name")
    private String fullName;

    private BigDecimal salary;

    @SerializedName(value = "started_on")
    private String startedOn;

    @SerializedName(value = "branch_name")
    private String branchName;

    public EmployeeImportDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
