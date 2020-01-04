package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarViewDto {

//    @XmlAttribute(name = "id")
    @XmlTransient
    private Long Id;

    @XmlAttribute(name = "make")
    private String Make;

    @XmlAttribute(name = "model")
    private String Model;

    @XmlAttribute(name = "travelled-distance")
    private Long TravelledDistance;

    @Override
    public String toString() {
        return "CarViewDto{" +
                "Make='" + Make + '\'' +
                ", Model='" + Model + '\'' +
                ", TravelledDistance=" + TravelledDistance +
                '}';
    }
}
