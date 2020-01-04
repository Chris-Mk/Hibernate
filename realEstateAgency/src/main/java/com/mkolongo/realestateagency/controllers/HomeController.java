package com.mkolongo.realestateagency.controllers;

import com.mkolongo.realestateagency.domain.model.view.OfferViewModel;
import com.mkolongo.realestateagency.service.OfferService;
import com.mkolongo.realestateagency.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private static final String FILE_PATH = "./templates/index.html";

    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public HomeController(@Qualifier("offer_service") OfferService offerService, ModelMapper modelMapper, FileUtil fileUtil) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @GetMapping("/")
    @ResponseBody
    public String home() throws IOException {
        final List<OfferViewModel> offers =
                modelMapper.map(offerService.getAllOffers(), new TypeToken<List<OfferViewModel>>() {}.getType());

        return fileUtil.readFile(FILE_PATH)
                .replace("{{offers}}", offers.isEmpty() ? "<p>There aren't any offers!</p>" :
                        offers.stream()
                        .map(offerViewModel -> String.format("""
                        <div class="apartment">
                            <p>Rent: %s</p>
                            <p>Type: %s</p>
                            <p>Commission: %s</p>
                        </div>
                       """, offerViewModel.getApartmentRent(),
                            offerViewModel.getApartmentType(),
                            offerViewModel.getAgencyCommission()))
                        .collect(Collectors.joining()));
    }
}
