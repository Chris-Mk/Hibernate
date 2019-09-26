package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierListSeedDto {

    @XmlElement(name = "supplier")
    private List<SupplierSeedDto> suppliers;

    @Override
    public String toString() {
        return "SupplierListSeedDto{" +
                "suppliers=" + suppliers +
                '}';
    }
}
