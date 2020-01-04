package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartViewDto {

    @XmlAttribute(name = "name")
    private String Name;

    @XmlAttribute(name = "price")
    private BigDecimal Price;

    @Override
    public String toString() {
        return "PartViewDto{" +
                "Name='" + Name + '\'' +
                ", Price=" + Price +
                '}';
    }
}
