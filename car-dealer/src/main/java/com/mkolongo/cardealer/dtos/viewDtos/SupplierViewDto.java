package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierViewDto {

    @XmlAttribute(name = "id")
    private Long Id;

    @XmlAttribute(name = "name")
    private String Name;

    @XmlAttribute(name = "parts-count")
    private int PartsCount;
}
