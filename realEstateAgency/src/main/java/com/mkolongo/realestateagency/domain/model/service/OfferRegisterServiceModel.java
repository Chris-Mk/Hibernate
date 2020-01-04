package com.mkolongo.realestateagency.domain.model.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class OfferRegisterServiceModel {

    private String id;

    @NotNull
    @DecimalMin("1")
    private BigDecimal apartmentRent;

    @NotBlank
    private String apartmentType;

    @NotNull
    @DecimalMin("0")
    @DecimalMax("100")
    private BigDecimal agencyCommission;
}
