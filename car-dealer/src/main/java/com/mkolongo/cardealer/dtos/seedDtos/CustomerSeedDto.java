package com.mkolongo.cardealer.dtos.seedDtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

@Getter
@Setter
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedDto {

    @XmlAttribute
    @NotNull(message = "Customer name cannot be empty!")
    private String name;

    @XmlElement(name = "birth-date")
    @NotNull(message = "Customer birth date cannot be empty!")
    private String birthDate;

    @XmlElement(name = "is-young-driver")
    @NotNull(message = "Invalid field!")
    private Boolean isYoungDriver;

    @Override
    public String toString() {
        return "CustomerSeedDto{" +
                "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", isYoungDriver=" + isYoungDriver +
                '}';
    }
}
