package com.mkolongo.productsshop.models;

import com.mkolongo.productsshop.models.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false, length = 15)
    private String name;

    @ManyToMany(targetEntity = Product.class, mappedBy = "categories")
    private Set<Product> products;
}
