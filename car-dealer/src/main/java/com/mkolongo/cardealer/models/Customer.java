package com.mkolongo.cardealer.models;

import com.mkolongo.cardealer.models.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "birth_date")
    private LocalDateTime birthDate;

    @Column(nullable = false, name = "is_young_driver")
    private Boolean isYoungDriver;

    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales;

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isYoungDriver=" + isYoungDriver +
                ", sales=" + sales +
                '}';
    }
}
