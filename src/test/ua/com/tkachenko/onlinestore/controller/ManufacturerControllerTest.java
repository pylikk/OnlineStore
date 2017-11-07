package ua.com.tkachenko.onlinestore.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.com.tkachenko.onlinestore.model.Goods;
import ua.com.tkachenko.onlinestore.model.Manufacturer;
import ua.com.tkachenko.onlinestore.service.GoodsService;
import ua.com.tkachenko.onlinestore.service.ManufacturerService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ManufacturerControllerTest {

    @Mock
    private GoodsService goodsService;

    @Mock
    private ManufacturerService manufacturerService;

    @InjectMocks
    private ManufacturerController manufacturerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(manufacturerController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testWelcome() throws Exception {

        List<Manufacturer> allManufacturers = new ArrayList<>();
        allManufacturers.add(new Manufacturer());
        allManufacturers.add(new Manufacturer());

        List<Goods> goods = new ArrayList<>();
        goods.add(new Goods());
        goods.add(new Goods());

        when(manufacturerService.findAll()).thenReturn(allManufacturers);
        when(goodsService.startedGoods()).thenReturn(goods);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("goods", hasSize(2)))
                .andExpect(model().attribute("allManufacturers", hasSize(2)));

    }

    @Test
    public void testAddManufacturer() throws Exception {

        Manufacturer manufacturer = new Manufacturer();

        mockMvc.perform(post("/admin/manufacturer/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/goods"));

    }

    @Test
    public void testEditManufacturer() throws Exception {

        Long id = 1L;

        Manufacturer manufacturer = new Manufacturer();

        when(manufacturerService.findById(id)).thenReturn(manufacturer);

        mockMvc.perform(post("/admin/edit_manufacturer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit_manufacturer"))
                .andExpect(model().attribute("manufacturer", instanceOf(Manufacturer.class)));

    }

    @Test
    public void testSaveManufacturer() throws Exception {

        mockMvc.perform(post("/admin/edit_manufacturer"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit_manufacturer"))
                .andExpect(model().attribute("manufacturer", instanceOf(Manufacturer.class)));

    }

    @Test
    public void testRemoveManufacturer() throws Exception {

        mockMvc.perform(post("/admin/remove_manufacturer/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/goods"));

    }

}