package com.mkolongo.realestateagency.service;

import com.mkolongo.realestateagency.domain.entity.Offer;
import com.mkolongo.realestateagency.domain.model.service.OfferFindServiceModel;
import com.mkolongo.realestateagency.domain.model.service.OfferRegisterServiceModel;
import com.mkolongo.realestateagency.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service("offer_service")
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public OfferServiceImpl(@Qualifier("offer_repo") OfferRepository offerRepository,
                            ModelMapper modelMapper,
                            Validator validator) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void registerOffer(OfferRegisterServiceModel offerRegisterServiceModel) {
        if (!validator.validate(offerRegisterServiceModel).isEmpty()) {
            throw new IllegalArgumentException("Something went wrong");
        }

        final Offer offer = modelMapper.map(offerRegisterServiceModel, Offer.class);
        offerRepository.saveAndFlush(offer);
    }

    @Override
    public List<OfferRegisterServiceModel> getAllOffers() {
        return modelMapper.map(offerRepository.findAll(), new TypeToken<List<OfferRegisterServiceModel>>() {}.getType());
    }

    @Override
    public void getOfferPriceAndApartmentType(OfferFindServiceModel offerFindServiceModel) {
        if (!validator.validate(offerFindServiceModel).isEmpty()) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        offerRepository.findAll()
                .stream()
                .filter(offer -> {
                    final BigDecimal totalRent = offer.getAgencyCommission()
                            .divide(new BigDecimal("100.00"), 2, RoundingMode.HALF_EVEN)
                            .multiply(offer.getApartmentRent())
                            .add(offer.getApartmentRent());

                    return offer.getApartmentType().equals(offerFindServiceModel.getApartmentType()) &&
                            offerFindServiceModel.getFamilyBudget().compareTo(totalRent) > 0;
                })
                .findFirst()
                .ifPresent(offerRepository::delete);
    }
}
