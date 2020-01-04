package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedDto {

    @XmlElement
    @NotNull(message = "Car make cannot be empty!")
    private String make;

    @XmlElement
    @NotNull(message = "Car model cannot be empty!")
    private String model;

    @XmlElement(name = "travelled-distance")
    @NotNull(message = "Car travelled distance cannot be empty!")
    private Long travelledDistance;

    @Override
    public String toString() {
        return "CarSeedDto{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", travelledDistance=" + travelledDistance +
                '}';
    }
}
