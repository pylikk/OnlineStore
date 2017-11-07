package ua.com.tkachenko.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.com.tkachenko.onlinestore.controller.GoodsController;
import ua.com.tkachenko.onlinestore.model.Goods;
import ua.com.tkachenko.onlinestore.model.Manufacturer;
import ua.com.tkachenko.onlinestore.service.GoodsService;
import ua.com.tkachenko.onlinestore.service.ManufacturerService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GoodsControllerTest {

    @Mock
    private GoodsService goodsService;

    @Mock
    private ManufacturerService manufacturerService;

    @InjectMocks
    private GoodsController goodsController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(goodsController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testGoods() throws Exception {

        List<Manufacturer> allManufacturers = new ArrayList<>();
        allManufacturers.add(new Manufacturer());
        allManufacturers.add(new Manufacturer());

        when(manufacturerService.findAll()).thenReturn((allManufacturers));

        mockMvc.perform(get("/admin/goods"))
                .andExpect(status().isOk())
                .andExpect(view().name("goods"))
                .andExpect(model().attribute("allManufacturers", hasSize(2)))
                .andExpect(model().attribute("goods", instanceOf(Goods.class)))
                .andExpect(model().attribute("manufacturer", instanceOf(Manufacturer.class)));
    }

    @Test
    public void testGoodsByManufacturer() throws Exception {

        String manufacturer = "Versace";

        List<Manufacturer> allManufacturers = new ArrayList<>();
        allManufacturers.add(new Manufacturer());
        allManufacturers.add(new Manufacturer());

        List<Goods> goods = new ArrayList<>();
        goods.add(new Goods());
        goods.add(new Goods());

        Manufacturer manufact = new Manufacturer();
        manufact.setGoods(goods);

        when(manufacturerService.findAll()).thenReturn((allManufacturers));
        when(manufacturerService.findByName(manufacturer)).thenReturn(manufact);

        mockMvc.perform(get("/goodsbymanufacturer/Versace"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("manufacturer", instanceOf(Manufacturer.class)))
                .andExpect(model().attribute("allManufacturers", hasSize(2)))
                .andExpect(model().attribute("goods", hasSize(2)));
    }

    @Test
    public void testManufacturers() throws Exception {

        String manuf = "Versace";

        List<Goods> listGoods = new ArrayList<>();
        listGoods.add(new Goods());
        listGoods.add(new Goods());

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setGoods(listGoods);

        List<Manufacturer> allManufacturers = new ArrayList<>();
        allManufacturers.add(new Manufacturer());
        allManufacturers.add(new Manufacturer());

        when(manufacturerService.findByName(manuf)).thenReturn(manufacturer);
        when(manufacturerService.findAll()).thenReturn(allManufacturers);

        mockMvc.perform(get("/admin/goods/Versace"))
                .andExpect(status().isOk())
                .andExpect(view().name("goods"))
                .andExpect(model().attribute("manuf", instanceOf(Manufacturer.class)))
                .andExpect(model().attribute("listGoods", hasSize(2)))
                .andExpect(model().attribute("allManufacturers", hasSize(2)))
                .andExpect(model().attribute("goods", instanceOf(Goods.class)));
    }

//    @Test
//    public void testAddGoods () throws Exception {
//
//        Goods goods = new Goods();
//        Goods returnGoods = new Goods();
//        Manufacturer manufacturer = new Manufacturer();
//
//        returnGoods.setId(1L);
//        returnGoods.setName("Bright Crystal");
//        returnGoods.setManufacturer(manufacturer);
//        returnGoods.setPrice(50);
//        returnGoods.setDescription("description");
//        returnGoods.setQuantity(10);
//        returnGoods.setName("/images/1.jpg");
//
//        when(goodsService.save(goods)).thenReturn(returnGoods);
//
//        mockMvc.perform(post("/admin/goods/add")
//                .param("id", "1")
//                .param("name", "Bright Crystal"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/admin/goods"));
//    }

}