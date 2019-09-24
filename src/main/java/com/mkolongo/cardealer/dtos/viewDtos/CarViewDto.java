package com.mkolongo.cardealer.dtos.viewDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarViewDto {
    private Long Id;
    private String Make;
    private String Model;
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
