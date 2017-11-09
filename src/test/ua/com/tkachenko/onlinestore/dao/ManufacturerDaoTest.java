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
public class ManufacturerDaoTest {

    @Autowired
    private ManufacturerDao manufacturerDao;

    @Autowired
    private GoodsDao goodsDao;

    @Before
    public void init () {

        Manufacturer versace = new Manufacturer("Versace", "Italy");
        versace.setId(1L);
        Manufacturer chanel = new Manufacturer("Chanel", "France");
        chanel.setId(2L);
        Manufacturer dior = new Manufacturer("Dior", "France");
        dior.setId(3L);

        manufacturerDao.save(versace);
        manufacturerDao.save(chanel);
        manufacturerDao.save(dior);

        Goods versence= new Goods("Versence", versace, "des", 40, 10, "image");
        versence.setId(1L);
        goodsDao.save(versence);
        Goods chance = new Goods("Chance", chanel, "des", 40, 10, "image");
        chance.setId(2L);
        goodsDao.save(chance);
        Goods jadore = new Goods("Jadore", dior, "des", 40, 10, "image");
        jadore.setId(3L);
        goodsDao.save(jadore);

        List<Goods> versaceGoods = new ArrayList<>();
        versaceGoods.add(versence);

        List<Goods> chanelGoods = new ArrayList<>();
        chanelGoods.add(chance);

        List<Goods> diorGoods = new ArrayList<>();
        diorGoods.add(jadore);

        versace.setGoods(versaceGoods);
        chanel.setGoods(chanelGoods);
        dior.setGoods(diorGoods);

        manufacturerDao.save(versace);
        manufacturerDao.save(chanel);
        manufacturerDao.save(dior);
    }

    @Test
    @Transactional
    public void testSave () {

        Manufacturer chloe = new Manufacturer("Chloe", "France");
        chloe.setId(4L);
        manufacturerDao.save(chloe);

        Goods love = new Goods("love", chloe, "des", 40, 10, "image");
        love.setId(4L);
        goodsDao.save(love);

        List<Goods> chloeGoods = new ArrayList<>();
        chloeGoods.add(love);

        chloe.setGoods(chloeGoods);
        manufacturerDao.save(chloe);

        Manufacturer result = manufacturerDao.findById(4L);
        assertEquals(chloe.getName(), result.getName());
    }

    @Test
    public void testFindAll () {

        List<Manufacturer> manufacturers = (List<Manufacturer>) manufacturerDao.findAll();
        assertEquals(3, manufacturers.size());
    }

    @Test
    public void testFindById() throws Exception {

        assertEquals("Versace", manufacturerDao.findById(1L).getName());
    }

    @Test
    public void testFindByName() throws Exception {

        assertEquals(1, manufacturerDao.findByName("Versace").getId());
    }

    @Test
    @Transactional
    public void testDelete () {
        goodsDao.delete(1L);
        manufacturerDao.delete(1L);
        assertNull(manufacturerDao.findById(1L));
    }
}