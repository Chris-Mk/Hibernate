package com.mkolongo.realestateagency.controllers;

import com.mkolongo.realestateagency.domain.model.binding.OfferRegisterBindingModel;
import com.mkolongo.realestateagency.domain.model.service.OfferRegisterServiceModel;
import com.mkolongo.realestateagency.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(@Qualifier("offer_service") OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/reg")
    public String getRegistrationForm() {
        return "register";
    }

    @PostMapping("/reg")
    public String register(OfferRegisterBindingModel offerRegisterBindingModel) {
        try {
            final OfferRegisterServiceModel offerRegisterServiceModel = modelMapper.map(offerRegisterBindingModel, OfferRegisterServiceModel.class);
            offerService.registerOffer(offerRegisterServiceModel);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return "redirect:/reg";
        }
        return "redirect:/";
    }
}
