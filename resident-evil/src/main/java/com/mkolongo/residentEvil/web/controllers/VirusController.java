package com.mkolongo.residentEvil.web.controllers;

import com.mkolongo.residentEvil.domain.entities.Creator;
import com.mkolongo.residentEvil.domain.entities.Magnitude;
import com.mkolongo.residentEvil.domain.entities.Mutation;
import com.mkolongo.residentEvil.domain.models.binding.VirusBindingModel;
import com.mkolongo.residentEvil.domain.models.service.VirusServiceModel;
import com.mkolongo.residentEvil.domain.models.view.VirusEditModel;
import com.mkolongo.residentEvil.service.CapitalService;
import com.mkolongo.residentEvil.service.VirusService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/viruses")
@RequiredArgsConstructor
public class VirusController {

    private final CapitalService capitalService;
    private final VirusService virusService;
    private final ModelMapper modelMapper;

    @GetMapping("/add")
    public ModelAndView addVirus(ModelAndView modelAndView) {
        modelAndView.addObject("virusBindingModel", new VirusBindingModel());

        modelAndView.addObject("capitals", capitalService.getAllCapitalNames());
        modelAndView.addObject("magnitudes", Magnitude.values());
        modelAndView.addObject("creators", Creator.values());
        modelAndView.addObject("mutations", Mutation.values());

        modelAndView.setViewName("add");

        return modelAndView;
    }

    @PostMapping("/add")
    public String addVirus(@Valid @ModelAttribute(name = "virusBindingModel") VirusBindingModel virusBindingModel,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        }

        final VirusServiceModel virusServiceModel = modelMapper.map(virusBindingModel, VirusServiceModel.class);
        virusService.spreadVirus(virusServiceModel);
        return "redirect:/viruses";
    }

    @GetMapping
    public ModelAndView showViruses(ModelAndView modelAndView) {
        modelAndView.addObject("viruses", virusService.getAllViruses());
        modelAndView.setViewName("all-viruses");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        virusService.deleteVirusById(id);
        return "redirect:/viruses";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView modelAndView) {
        final VirusServiceModel serviceModel = virusService.getVirusById(id);
        final VirusEditModel viewModel = modelMapper.map(serviceModel, VirusEditModel.class);

        modelAndView.addObject("capitals", capitalService.getAllCapitalNames());
        modelAndView.addObject("magnitudes", Magnitude.values());
        modelAndView.addObject("creators", Creator.values());
        modelAndView.addObject("mutations", Mutation.values());
        modelAndView.addObject("virusEditModel", viewModel);
        modelAndView.setViewName("edit-virus");

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid @ModelAttribute(name = "virusEditModel") VirusEditModel virusEditModel,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-virus";
        }

        final VirusServiceModel serviceModel = modelMapper.map(virusEditModel, VirusServiceModel.class);
        virusService.editVirus(serviceModel);

        return "redirect:/viruses";
    }
}
