package com.mkolongo.realestateagency.domain.model.view;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OfferViewModel {

    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;
}
