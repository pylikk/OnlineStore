package ua.com.tkachenko.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.tkachenko.onlinestore.model.Goods;
import ua.com.tkachenko.onlinestore.model.Manufacturer;
import ua.com.tkachenko.onlinestore.service.GoodsService;
import ua.com.tkachenko.onlinestore.service.ManufacturerService;
import ua.com.tkachenko.onlinestore.service.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class GoodsController {

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String goods (Model model) {
        model.addAttribute("allManufacturers",manufacturerService.findAll());
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("goods", new Goods());

        return "goods";
    }

    @RequestMapping(value = "/goodsbymanufacturer/{manufacturer}")
    public String goodsByManufacturer (@PathVariable("manufacturer") String manufacturer, Model model) {
        model.addAttribute("manufacturer", manufacturerService.findByName(manufacturer));
        model.addAttribute("allManufacturers",manufacturerService.findAll());
        model.addAttribute("goods", manufacturerService.findByName(manufacturer).getGoods());

        return "index";
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

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile (@RequestParam("file") MultipartFile file, @RequestParam("goods_id") String id) {
        String path = "C:/Users/Ьф/IdeaProjects/OnlineStore/src/main/webapp/resources/images/";
        String fileName = path + id + ".jpg";

        if (!file.isEmpty()) {
            File uploadedFile = new File(fileName);
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/goods";
    }

}
