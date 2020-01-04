package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CarPartViewDto {
    private CarViewDto car;

    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private Set<PartViewDto> parts;
}
