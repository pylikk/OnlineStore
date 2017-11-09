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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class GoodsDaoTest {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private ManufacturerDao manufacturerDao;

    @Before
    public void init () {

        Manufacturer manufacturer = new Manufacturer("manufacturer", "Italy");
        manufacturer.setId(1L);
        manufacturerDao.save(manufacturer);

        Goods brightCrystal= new Goods("Bright Crystal", manufacturer, "des", 40, 10, "image");
        brightCrystal.setId(1L);
        Goods crystalNoir = new Goods("Crystal Noir", manufacturer, "des", 40, 10, "image");
        crystalNoir.setId(2L);
        Goods chance = new Goods("Chance", manufacturer, "des", 40, 10, "image");
        chance.setId(3L);

        goodsDao.save(brightCrystal);
        goodsDao.save(crystalNoir);
        goodsDao.save(chance);

        List<Goods> goods = new ArrayList<>();
        goods.add(brightCrystal);
        goods.add(crystalNoir);
        goods.add(chance);

        manufacturer.setGoods(goods);
        manufacturerDao.save(manufacturer);
    }

    @Test
    public void testFindAll () {

        assertEquals(3, ((List<Goods>)goodsDao.findAll()).size());
    }

    @Test
    public void testFindOne () {

        Long id = 1L;
        Goods goods = goodsDao.findOne(id);

        assertEquals("Bright Crystal", goods.getName());
    }

    @Test
    @Transactional
    public void testSave () {

        Manufacturer manufacturer = manufacturerDao.findOne(1L);

        Goods chloe = new Goods("Chloe", manufacturer, "des", 40, 10, "image");
        chloe.setId(4L);

        goodsDao.save(chloe);
        Goods goods = goodsDao.findOne(chloe.getId());

        assertEquals(goods.getName(), chloe.getName());
    }

    @Test
    @Transactional
    public void testDelete () {

        goodsDao.delete(2L);

        assertNull(goodsDao.findOne(2L));
    }

    @Test
    public void testFindByNameLike () throws Exception {

        List<Goods> goods = goodsDao.findByNameLike("%Crystal%");

        assertEquals(2, goods.size());
    }
}