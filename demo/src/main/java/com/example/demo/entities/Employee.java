package com.example.demo.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "employees")
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private LocalDate birthday;
    private String address;
    private boolean isOnHoliday;
    private Employee manager;
    private Set<Employee> employeesInChargeOf;

    public Employee() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(precision = 19, scale = 2)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "is_on_holiday")
    public boolean isOnHoliday() {
        return isOnHoliday;
    }

    public void setOnHoliday(boolean onHoliday) {
        isOnHoliday = onHoliday;
    }

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @OneToMany
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    public Set<Employee> getEmployeesInChargeOf() {
        return employeesInChargeOf;
    }

    public void setEmployeesInChargeOf(Set<Employee> employeesInChargeOf) {
        this.employeesInChargeOf = employeesInChargeOf;
    }
}
