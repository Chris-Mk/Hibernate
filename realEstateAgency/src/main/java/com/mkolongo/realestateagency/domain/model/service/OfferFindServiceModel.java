package com.mkolongo.realestateagency.domain.model.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class OfferFindServiceModel {

    @NotNull
    @DecimalMin("1")
    private BigDecimal familyBudget;

    @NotBlank
    private String apartmentType;

    @NotBlank
    private String familyName;
}
