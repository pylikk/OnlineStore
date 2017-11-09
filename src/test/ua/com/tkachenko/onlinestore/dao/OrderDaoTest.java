package ua.com.tkachenko.onlinestore.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tkachenko.onlinestore.model.Goods;
import ua.com.tkachenko.onlinestore.model.Manufacturer;
import ua.com.tkachenko.onlinestore.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ManufacturerDao manufacturerDao;

    @Autowired
    private GoodsDao goodsDao;

    @Before
    public void init () {

        Manufacturer versace = new Manufacturer("Versace", "Italy");
        versace.setId(1L);
        manufacturerDao.save(versace);

        Goods versence= new Goods("Versence", versace, "des", 40, 10, "image");
        versence.setId(1L);
        goodsDao.save(versence);

        List<Goods> goods = new ArrayList<>();
        goods.add(versence);
        versace.setGoods(goods);
        manufacturerDao.save(versace);

        Order order1 = new Order("phone", "address", "firstname", "lastname", new Date(), "info1", "new", versence);
        order1.setId(1L);
        Order order2 = new Order("phone", "address", "firstname", "lastname", new Date(), "info2", "new", versence);
        order2.setId(2L);
        Order order3 = new Order("phone", "address", "firstname", "lastname", new Date(), "info3", "new", versence);
        order3.setId(3L);

        orderDao.save(order1);
        orderDao.save(order2);
        orderDao.save(order3);
    }

    @Test
    public void testStatusList() throws Exception {

        assertEquals(3, orderDao.statusList().size());
    }

    @Test
    @Transactional
    public void testSave () {

        Goods versence = goodsDao.findOne(1L);
        Order order4 = new Order("phone", "address", "firstname", "lastname", new Date(), "test_order", "new", versence);
        order4.setId(4L);
        orderDao.save(order4);

        assertEquals("test_order", orderDao.findOne(4L).getOrder_info());
    }

    @Test
    public void testFindAll () {

        assertEquals(3, ((List<Order>)orderDao.findAll()).size());
    }

    @Test
    @Transactional
    public void testDelete () {

        orderDao.delete(2L);
        assertNull(orderDao.findOne(2L));
    }

    @Test
    public void testFindOne () {

        Order order = orderDao.findOne(1L);
        assertEquals("info1", order.getOrder_info());
    }
}