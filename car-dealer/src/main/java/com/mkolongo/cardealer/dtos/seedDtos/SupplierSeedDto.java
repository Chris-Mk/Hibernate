package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "supplier")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SupplierSeedDto {

    @XmlAttribute
    @NotNull(message = "Supplier name cannot be empty!")
    private String name;

    @XmlAttribute(name = "is-importer")
    @NotNull(message = "Required field!")
    private boolean isImporter;

    @Override
    public String toString() {
        return "SupplierSeedDto{" +
                "name='" + name + '\'' +
                ", isImporter=" + isImporter +
                '}';
    }
}
