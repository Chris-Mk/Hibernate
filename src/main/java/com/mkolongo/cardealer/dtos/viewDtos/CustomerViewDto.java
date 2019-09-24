package com.mkolongo.cardealer.dtos.viewDtos;

import com.mkolongo.cardealer.models.Sale;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class CustomerViewDto {

    private Long Id;
    private String Name;
    private String BirthDate;
    private Boolean IsYoungDriver;
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
