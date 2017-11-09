package ua.com.tkachenko.onlinestore.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.tkachenko.onlinestore.model.Goods;
import ua.com.tkachenko.onlinestore.model.Manufacturer;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class GoodsDaoTest {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private ManufacturerDao manufacturerDao;

    @Test
    public void testFindByNameLike () throws Exception {

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1);
        manufacturer.setName("Versace");
        manufacturer.setCountry("Italy");
        manufacturerDao.save(manufacturer);

        Goods brightCrystal = new Goods();
        brightCrystal.setId(1);
        brightCrystal.setName("Bright Crystal");
        brightCrystal.setQuantity(10);
        brightCrystal.setDescription("adad");
        brightCrystal.setPrice(40);
        brightCrystal.setManufacturer(manufacturer);
        brightCrystal.setImage("image");
        goodsDao.save(brightCrystal);

        Goods crystalNoir = new Goods();
        crystalNoir.setId(2);
        crystalNoir.setName("Crystal Noir");
        crystalNoir.setQuantity(10);
        crystalNoir.setDescription("adad");
        crystalNoir.setPrice(40);
        crystalNoir.setManufacturer(manufacturer);
        crystalNoir.setImage("image");
        goodsDao.save(crystalNoir);

        Goods chance = new Goods();
        chance.setId(3);
        chance.setName("Chance");
        chance.setQuantity(10);
        chance.setDescription("adad");
        chance.setPrice(40);
        chance.setManufacturer(manufacturer);
        chance.setImage("image");
        goodsDao.save(chance);

        List<Goods> goods = goodsDao.findByNameLike("%Crystal%");

        assertEquals(2, goods.size());
    }

}