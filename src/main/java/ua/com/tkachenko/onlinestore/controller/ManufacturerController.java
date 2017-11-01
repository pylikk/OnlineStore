package ua.com.tkachenko.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.tkachenko.onlinestore.model.Manufacturer;
import ua.com.tkachenko.onlinestore.service.ManufacturerService;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @RequestMapping(value = {"/","welcome"}, method = RequestMethod.GET)
    public String welcome (Model model) {
        model.addAttribute("allManufacturers", manufacturerService.findAll());
        return "index";
    }



    @RequestMapping(value = "/manufacturer/add", method = RequestMethod.POST)
    public String addManufacturer (@ModelAttribute("manufacturer") Manufacturer manufacturer) {
        manufacturerService.save(manufacturer);
        return "redirect:/goods";
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
