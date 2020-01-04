package com.mkolongo.residentEvil.web.controllers;

import com.mkolongo.residentEvil.domain.models.binding.UserRegisterBindingModel;
import com.mkolongo.residentEvil.domain.models.service.UserServiceModel;
import com.mkolongo.residentEvil.domain.models.view.UserViewModel;
import com.mkolongo.residentEvil.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.addObject("bindingModel", new UserRegisterBindingModel());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute(name = "bindingModel") UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        final UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        userService.register(userServiceModel);

        return "redirect:/users/login";
    }

    @GetMapping
    public ModelAndView users(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.setViewName("all-users");

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editRole(@PathVariable Long id, ModelAndView modelAndView) {
        final UserViewModel user = userService.getUserById(id);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("user-role-edit");

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam(required = false, name = "moderator") boolean moderator,
                       @RequestParam(required = false, name = "admin") boolean admin) {

        userService.editRoles(id, moderator, admin);

        return "redirect:/users";
    }
}
