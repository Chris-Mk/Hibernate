package com.mkolongo.cardealer.models;

import com.mkolongo.cardealer.models.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "parts")
@Setter
@Getter
@NoArgsConstructor
public class Part extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @ManyToMany(mappedBy = "parts")
    private Set<Car> cars;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;
}
