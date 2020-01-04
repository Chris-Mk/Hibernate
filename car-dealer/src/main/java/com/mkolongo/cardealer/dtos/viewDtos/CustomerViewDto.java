package com.mkolongo.cardealer.dtos.viewDtos;

import com.mkolongo.cardealer.models.Sale;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Set;

@Setter
@Getter
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerViewDto {

    @XmlElement(name = "id")
    private Long Id;

    @XmlElement(name = "name")
    private String Name;

    @XmlElement(name = "birth-date")
    private String BirthDate;

    @XmlElement(name = "is-young-driver")
    private Boolean IsYoungDriver;

    @XmlTransient
    private Set<Sale> Sales;

    @Override
    public String toString() {
        return "CustomerViewDto{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", BirthDate=" + BirthDate +
                ", IsYoungDriver=" + IsYoungDriver +
                ", Sales=" + Sales +
                '}';
    }
}
