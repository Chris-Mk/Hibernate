package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarPartListViewDto {

//    @XmlElement(name = "cars")
    private List<CarPartViewDto> cars;
}
