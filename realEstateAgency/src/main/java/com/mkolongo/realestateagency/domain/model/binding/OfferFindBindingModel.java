package com.mkolongo.realestateagency.domain.model.binding;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OfferFindBindingModel {

    private BigDecimal familyBudget;
    private String apartmentType;
    private String familyName;
}
