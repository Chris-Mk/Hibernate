package com.mkolongo.realestateagency.service;

import com.mkolongo.realestateagency.domain.model.service.OfferFindServiceModel;
import com.mkolongo.realestateagency.domain.model.service.OfferRegisterServiceModel;

import java.util.List;

public interface OfferService {
    void registerOffer(OfferRegisterServiceModel offerRegisterServiceModel);

    List<OfferRegisterServiceModel> getAllOffers();

    void getOfferPriceAndApartmentType(OfferFindServiceModel offerFindServiceModel);
}
