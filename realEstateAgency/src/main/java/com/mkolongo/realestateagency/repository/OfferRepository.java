package com.mkolongo.realestateagency.repository;

import com.mkolongo.realestateagency.domain.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository("offer_repo")
public interface OfferRepository extends JpaRepository<Offer, String> {
}
