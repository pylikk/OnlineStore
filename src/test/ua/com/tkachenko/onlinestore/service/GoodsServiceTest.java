package ua.com.tkachenko.onlinestore.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.tkachenko.onlinestore.dao.GoodsDao;
import ua.com.tkachenko.onlinestore.model.Goods;
import ua.com.tkachenko.onlinestore.model.Manufacturer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GoodsServiceTest {

    @Mock
    private GoodsDao goodsDao;

    @InjectMocks
    private GoodsService goodsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindGoodsById() throws Exception {

        Long id = anyLong();
        Goods goods = new Goods();
        when(goodsDao.findOne(id)).thenReturn(goods);

        assertEquals(goods, goodsDao.findOne(id));
        verify(goodsDao).findOne(id);
    }

    @Test
    public void testSave() throws Exception {

        Goods goods = new Goods();

        when(goodsDao.save(goods)).thenReturn(goods);
        assertEquals(goods, goodsDao.save(goods));

        verify(goodsDao).save(goods);
    }

    @Test
    public void testRemove() throws Exception {

        Long id = anyLong();

        goodsService.remove(id);
        verify(goodsDao).delete(id);
    }

    @Test
    public void testSearch() throws Exception {

        String search = anyString();
        List<Goods> goods = new ArrayList<>();
        goods.add(new Goods());
        goods.add(new Goods());

        when(goodsDao.findByNameLike("%" + search + "%")).thenReturn(goods);
        assertEquals(goods, goodsService.search(search));

        verify(goodsDao).findByNameLike("%" + search + "%");
    }

    @Test
    public void testStartedGoods() throws Exception {

        List<Goods> goods = new ArrayList<>();
        Manufacturer manufacturer = new Manufacturer("Versace","Italy");
        manufacturer.setId(1L);
        Goods someGoods;
        for (long i = 0; i < 20; i++) {
            someGoods = new Goods("someGoods", manufacturer, "des", 40, 10, "image");
            someGoods.setId(i);
            goods.add(someGoods);
        }

        when(goodsDao.findAll()).thenReturn(goods);

        assertEquals(5, goodsService.startedGoods().size());
        verify(goodsDao).findAll();
    }

    @Test
    public void testFindAll() throws Exception {

        Iterable<Goods> goods = new ArrayList<>();
        assertEquals(goods, goodsService.findAll());
        verify(goodsDao).findAll();
    }
}