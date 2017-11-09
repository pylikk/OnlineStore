package ua.com.tkachenko.onlinestore.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Or;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.com.tkachenko.onlinestore.model.Goods;
import ua.com.tkachenko.onlinestore.model.Manufacturer;
import ua.com.tkachenko.onlinestore.model.Order;
import ua.com.tkachenko.onlinestore.service.GoodsService;
import ua.com.tkachenko.onlinestore.service.ManufacturerService;
import ua.com.tkachenko.onlinestore.service.OrderService;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private GoodsService goodsService;

    @Mock
    private ManufacturerService manufacturerService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testOrders() throws Exception {

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());

        when(orderService.allOrders()).thenReturn(orders);

        mockMvc.perform(get("/admin/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders"))
                .andExpect(model().attribute("allOrders", hasSize(2)));
    }

    @Test
    public void testCart() throws Exception {

        String goodsId = "1";
        Long id = 1L;
        Goods goods = new Goods();

        when(goodsService.findGoodsById(id)).thenReturn(goods);

        mockMvc.perform(get("/cart")
            .param("goods_id", goodsId))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andExpect(model().attribute("goods", instanceOf(Goods.class)))
                .andExpect(model().attribute("order", instanceOf(Order.class)));
    }

    @Test
    public void testMakeorder() throws Exception {

        Order order = new Order();
        order.setOrder_status("new");

        orderService.save(order);
        verify(orderService).save(order);

        mockMvc.perform(post("/makeorder"))
                .andExpect(status().is3xxRedirection());
//                .andExpect(view().name("redirect:/congratulations?order_id="));


    }

    @Test
    public void testCongratulations() throws Exception {

        mockMvc.perform(get("/congratulations"))
                .andExpect(status().isOk())
                .andExpect(view().name("congratulations"));
    }

    @Test
    public void testAddOrder() throws Exception {

        Order order = new Order();

        mockMvc.perform(post("/admin/order/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/orders"));

    }

    @Test
    public void testRemoveOrder() throws Exception {

        mockMvc.perform(post("/admin/remove_order/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/orders"));
    }

    @Test
    public void testEditOrder() throws Exception {

        Long id = 1L;

        List<Goods> allGoods = new ArrayList<>();
        allGoods.add(new Goods());
        allGoods.add(new Goods());

        Order order = new Order();

        when(goodsService.findAll()).thenReturn(allGoods);
        when(orderService.findOrderById(id)).thenReturn(order);

        mockMvc.perform(post("/admin/edit_order/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit_order"))
                .andExpect(model().attribute("allGoods", hasSize(2)))
                .andExpect(model().attribute("order", instanceOf(Order.class)));
    }

    @Test
    public void testSaveOrder() throws Exception {

        List<Manufacturer> allManufacturers = new ArrayList<>();
        allManufacturers.add(new Manufacturer());
        allManufacturers.add(new Manufacturer());

        List<String> statusList = new ArrayList<>();
        statusList.add("new");
        statusList.add("processed");
        statusList.add("completed");

        List<Goods> allGoods = new ArrayList<>();
        allGoods.add(new Goods());
        allGoods.add(new Goods());

        when(manufacturerService.findAll()).thenReturn(allManufacturers);
        when(goodsService.findAll()).thenReturn(allGoods);
        when(orderService.statusList()).thenReturn(statusList);

        mockMvc.perform(post("/admin/edit_order"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit_order"))
                .andExpect(model().attribute("order", instanceOf(Order.class)))
                .andExpect(model().attribute("allManufacturers", hasSize(2)))
                .andExpect(model().attribute("statusList", hasSize(3)))
                .andExpect(model().attribute("allGoods", hasSize(2)));

    }

}