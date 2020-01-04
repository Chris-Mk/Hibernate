package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarListSeedDto {

    @XmlElement(name = "car")
    private List<CarSeedDto> cars;
}
