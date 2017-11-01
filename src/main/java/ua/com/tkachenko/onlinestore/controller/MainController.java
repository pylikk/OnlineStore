package ua.com.tkachenko.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.tkachenko.onlinestore.model.Goods;
import ua.com.tkachenko.onlinestore.model.Manufacturer;
import ua.com.tkachenko.onlinestore.model.User;
import ua.com.tkachenko.onlinestore.service.GoodsService;
import ua.com.tkachenko.onlinestore.service.ManufacturerService;
import ua.com.tkachenko.onlinestore.service.SecurityService;
import ua.com.tkachenko.onlinestore.service.UserService;
import ua.com.tkachenko.onlinestore.validator.UserValidator;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration (Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration (@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm,bindingResult);
        if (bindingResult.hasErrors())
            return "registration";

        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(),userForm.getConfirmPassword());

        return "redirect:/user/cart";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login (Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error","Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }



    @RequestMapping(value = "/admin")
    public String admin () {
        return "admin";
    }
}
