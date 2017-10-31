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
    ManufacturerService manufacturerService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserValidator userValidator;

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

    @RequestMapping(value = "/manufacturer/add", method = RequestMethod.POST)
    public String addManufacturer (@ModelAttribute("manufacturer") Manufacturer manufacturer) {
        manufacturerService.save(manufacturer);
        return "redirect:/goods";
    }


    @RequestMapping(value = {"/","welcome"}, method = RequestMethod.GET)
    public String welcome (Model model) {
        return "index";
    }

    @RequestMapping(value = "/admin")
    public String admin () {
        return "admin";
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String goods (Model model) {
        model.addAttribute("allManufacturers",manufacturerService.findAll());
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("goods", new Goods());

        return "goods";
    }

    @RequestMapping(value = "goods/{manufacturer}", method = RequestMethod.GET)
    public String manufacturers (@PathVariable("manufacturer") String manufacturer, Model model) {
        model.addAttribute("manuf", manufacturerService.findByName(manufacturer));
        model.addAttribute("listGoods",manufacturerService.findByName(manufacturer).getGoods());

        model.addAttribute("allManufacturers",manufacturerService.findAll());
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("goods", new Goods());

        return "goods";
    }

    @RequestMapping(value = "goods/add", method = RequestMethod.POST)
    public String addGoods (@ModelAttribute("goods") Goods goods) {
        goodsService.save(goods);

        return "redirect:/goods";
    }

    @RequestMapping(value = "/remove_goods/{id}")
    public String removeGoods (@PathVariable("id") long id) {
        goodsService.remove(id);

        return "redirect:/goods";
    }

    @RequestMapping("/edit_goods/{id}")
    public String editGoods (@PathVariable("id") long id, Model model) {
        model.addAttribute("allManufacturers",manufacturerService.findAll());
        model.addAttribute("goods", goodsService.findGoodsById(id));

        return "edit_goods";
    }

    @RequestMapping("/edit_goods")
    public String addGoods (Model model) {
        model.addAttribute("goods", new Goods());
        model.addAttribute("allManufacturers",manufacturerService.findAll());

        return "edit_goods";
    }

    @RequestMapping("/edit_manufacturer/{id}")
    public String editManufacturer(@PathVariable("id") long id, Model model) {
        model.addAttribute("manufacturer",manufacturerService.findById(id));

        return "edit_manufacturer";
    }

    @RequestMapping("/edit_manufacturer")
    public String addManufacturer (Model model) {
        model.addAttribute("manufacturer", new Manufacturer());

        return "edit_manufacturer";
    }

    @RequestMapping("/remove_manufacturer/{id}")
    public String removeManufacturer (@PathVariable("id") long id, Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        manufacturerService.remove(id);

        return "redirect:/goods";
    }

}
