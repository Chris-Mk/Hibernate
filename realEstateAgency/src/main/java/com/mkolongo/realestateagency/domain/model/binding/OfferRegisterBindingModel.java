package com.mkolongo.realestateagency.domain.model.binding;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OfferRegisterBindingModel {

    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;
}
