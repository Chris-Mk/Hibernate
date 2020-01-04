package com.mkolongo.cardealer.models;

import com.mkolongo.cardealer.models.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
public class Supplier extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(name = "is_imported", nullable = false)
    private boolean isImporter;

    @OneToMany(mappedBy = "supplier")
    private Set<Part> parts;

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", isImporter=" + isImporter +
                ", parts=" + parts +
                '}';
    }
}
