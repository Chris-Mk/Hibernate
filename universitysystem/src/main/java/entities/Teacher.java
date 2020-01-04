package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "TR")
public class Teacher extends Person {

    private String email;
    private BigDecimal hourlySalary;
    private Set<Course> courses;

    public Teacher() {
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "hourly_salary")
    public BigDecimal getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(BigDecimal hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Course.class)
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
