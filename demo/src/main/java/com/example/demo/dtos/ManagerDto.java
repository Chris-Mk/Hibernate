package com.example.demo.dtos;

import com.example.demo.entities.Employee;

import java.util.Set;

public class ManagerDto {
    private String firstName;
    private String lastName;
    private Set<Employee> employeesInChargeOf;

    public ManagerDto(String firstName, String lastName, Set<Employee> employeesInChargeOf) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeesInChargeOf = employeesInChargeOf;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Employee> getEmployeesInChargeOf() {
        return employeesInChargeOf;
    }

    public void setEmployeesInChargeOf(Set<Employee> employeesInChargeOf) {
        this.employeesInChargeOf = employeesInChargeOf;
    }
}
