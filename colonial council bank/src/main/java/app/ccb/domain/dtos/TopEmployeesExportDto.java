package app.ccb.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class TopEmployeesExportDto {

    private String fullName;
    private BigDecimal salary;
    private LocalDate startedOn;
    private Set<String> clientsNames;

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

    public LocalDate getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDate startedOn) {
        this.startedOn = startedOn;
    }

    public Set<String> getClientsNames() {
        return clientsNames;
    }

    public void setClientsNames(Set<String> clientsNames) {
        this.clientsNames = clientsNames;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Full Name: %s%n", getFullName()))
                .append(String.format("Salary: %s%n", getSalary()))
                .append(String.format("Started On: %s%n", getStartedOn()))
                .append("Clients:").append(System.lineSeparator());

        for (String clientsName : clientsNames) {
            builder.append("\t").append(clientsName).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
