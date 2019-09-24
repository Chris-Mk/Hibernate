package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SupplierSeedDto {

    @NotNull(message = "Supplier name cannot be empty!")
    private String name;

    @NotNull(message = "Required field!")
    private Boolean isImporter;

    @Override
    public String toString() {
        return "SupplierSeedDto{" +
                "name='" + name + '\'' +
                ", isImporter=" + isImporter +
                '}';
    }
}
