package ua.com.tkachenko.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.tkachenko.onlinestore.dao.GoodsDao;
import ua.com.tkachenko.onlinestore.model.Goods;

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public Goods findGoodsById (long id) {
        return goodsDao.findOne(id);
    }

    public void save(Goods goods) {
        goodsDao.save(goods);
    }

    public void remove (long id) {
        goodsDao.delete(id);
    }
}
