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
        List<Goods> goods = (List<Goods>) goodsDao.findAll();
        List<Goods> startedGoods = new ArrayList<>();

        if (goods.size() < 6)
            return goods;

        for (int i = 0; i < 5; ) {
            int random = (int) (Math.random() * goods.size());
            if (!startedGoods.contains(goods.get(random))) {
                startedGoods.add(goods.get(random));
                i++;
            }
        }
        return startedGoods;
    }

    public Iterable<Goods> findAll() {
        return goodsDao.findAll();
    }
}
