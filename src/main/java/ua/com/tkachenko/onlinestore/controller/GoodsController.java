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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GoodsController {

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/admin/goods", method = RequestMethod.GET)
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

    @RequestMapping(value = "/admin/goods/{manufacturer}", method = RequestMethod.GET)
    public String manufacturers (@PathVariable("manufacturer") String manufacturer, Model model) {
        model.addAttribute("manuf", manufacturerService.findByName(manufacturer));
        model.addAttribute("listGoods",manufacturerService.findByName(manufacturer).getGoods());
        model.addAttribute("allManufacturers",manufacturerService.findAll());
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("goods", new Goods());

        return "goods";
    }

    @RequestMapping(value = "/admin/goods/add", method = RequestMethod.POST)
    public String addGoods (@ModelAttribute("goods") Goods goods, @RequestParam("file") MultipartFile file, @RequestParam("goods_id") String id) {

        Goods savedGoods = goodsService.save(goods);
        String fileName = "C:/Users/Ьф/IdeaProjects/OnlineStore/src/main/webapp/resources/images/"+savedGoods.getId()+".jpg";
        savedGoods.setImage(fileName);
        goodsService.save(savedGoods);

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


        return "redirect:/admin/goods";
    }

    @RequestMapping(value = "/admin/remove_goods/{id}")
    public String removeGoods (@PathVariable("id") long id) {
        goodsService.remove(id);

        return "redirect:/admin/goods";
    }

    @RequestMapping("/admin/edit_goods/{id}")
    public String editGoods (@PathVariable("id") long id, Model model) {
        model.addAttribute("allManufacturers",manufacturerService.findAll());
        model.addAttribute("goods", goodsService.findGoodsById(id));

        return "edit_goods";
    }

    @RequestMapping("/admin/edit_goods")
    public String addGoods (Model model) {
        model.addAttribute("goods", new Goods());
        model.addAttribute("allManufacturers", manufacturerService.findAll());

        return "edit_goods";
    }

    @RequestMapping(value = "/search")
    public String search (@RequestParam("search") String search, Model model) {
        List<Goods> goods = goodsService.search(search);
        if (goods != null) {
            model.addAttribute("goods", goods);
        }
        model.addAttribute("allManufactures", manufacturerService.findAll());
        return "index";
    }


}
