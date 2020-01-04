package com.mkolongo.realestateagency.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(name = "apartment_rent", precision = 10, scale = 2, nullable = false)
    private BigDecimal apartmentRent;

    @Column(name = "apartment_type", nullable = false)
    private String apartmentType;

    @Column(name = "agency_commission", precision = 10, scale = 2, nullable = false)
    private BigDecimal agencyCommission;
}
