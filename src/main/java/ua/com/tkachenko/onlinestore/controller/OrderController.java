package ua.com.tkachenko.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.tkachenko.onlinestore.model.Order;
import ua.com.tkachenko.onlinestore.service.GoodsService;
import ua.com.tkachenko.onlinestore.service.ManufacturerService;
import ua.com.tkachenko.onlinestore.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ManufacturerService manufacturerService;

    @RequestMapping("/orders")
    public String orders (Model model) {
        model.addAttribute("allOrders", orderService.allOrders());

        return "orders";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart (@RequestParam("goods_id") long id, Model model) {
        model.addAttribute("goods", goodsService.findGoodsById(id));
        model.addAttribute("order", new Order());

        return "cart";
    }

    @RequestMapping(value = "/makeorder", method = RequestMethod.POST)
    public String makeorder (@ModelAttribute("order") Order order){
        orderService.save(order);

        return "redirect:/congratulations?order_id=" + order.getId();
    }

    @RequestMapping(value = "/congratulations", method = RequestMethod.GET)
    public String congratulations (@ModelAttribute("order_id") String id) {

        return "congratulations";
    }

    @RequestMapping(value = "order/add", method = RequestMethod.POST)
    public String addOrder (@ModelAttribute("order") Order order) {
        orderService.save(order);

        return "redirect:/orders";
    }

    @RequestMapping(value = "/remove_order/{id}")
    public String removeOrder (@PathVariable("id") long id) {
        orderService.remove(id);

        return "redirect:/orders";
    }

    @RequestMapping("/edit_order/{id}")
    public String editOrder (@PathVariable("id") long id, Model model) {
        model.addAttribute("allManufacturers",manufacturerService.findAll());
        model.addAttribute("order", orderService.findOrderById(id));

        return "edit_order";
    }

    @RequestMapping("/edit_order")
    public String addOrder (Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("allManufacturers",manufacturerService.findAll());

        return "edit_order";
    }
}
