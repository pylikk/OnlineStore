package ua.com.tkachenko.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.tkachenko.onlinestore.dao.GoodsDao;
import ua.com.tkachenko.onlinestore.model.Goods;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public Goods findGoodsById (long id) {
        return goodsDao.findOne(id);
    }

    public Goods save(Goods goods) {
        return goodsDao.save(goods);
    }

    public void remove (long id) {
        goodsDao.delete(id);
    }

    public List<Goods> search (String search) {
        return goodsDao.findByNameLike("%" + search + "%");
    }

    public List<Goods> startedGoods () {
        List<Goods> startedGoods = new ArrayList<>();
        startedGoods.add(goodsDao.findOne(1L));
//        startedGoods.add(goodsDao.findOne(2L));
        startedGoods.add(goodsDao.findOne(3L));
        startedGoods.add(goodsDao.findOne(4L));
        startedGoods.add(goodsDao.findOne(5L));

        for (Goods goods : startedGoods) {
            System.out.println(goods.getName());
        }
        return startedGoods;
    }
}
