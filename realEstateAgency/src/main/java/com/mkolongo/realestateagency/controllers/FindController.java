package com.mkolongo.realestateagency.controllers;

import com.mkolongo.realestateagency.domain.model.binding.OfferFindBindingModel;
import com.mkolongo.realestateagency.domain.model.service.OfferFindServiceModel;
import com.mkolongo.realestateagency.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public FindController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/find")
    public String getFindForm() {
        return "find";
    }

    @PostMapping("/find")
    public String find(OfferFindBindingModel offerFindBindingModel) {
        try {
            final OfferFindServiceModel offerFindServiceModel = modelMapper.map(offerFindBindingModel, OfferFindServiceModel.class);
            offerService.getOfferPriceAndApartmentType(offerFindServiceModel);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return "redirect:/find";
        }
        return "redirect:/";
    }
}
