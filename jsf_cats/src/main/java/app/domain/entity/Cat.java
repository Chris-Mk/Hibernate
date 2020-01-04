package app.domain.entity;

import app.domain.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cats")
@Getter
@Setter
public class Cat extends BaseEntity {

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String breed;

    @Column
    private String color;

    @Column
    private int age;

    @Column
    private String gender;

    @Column
    private BigDecimal price;

    @Column
    private LocalDate addedOn;

    @Column
    private boolean hasPassport;

}
