package ua.com.tkachenko.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.tkachenko.onlinestore.dao.GoodsDao;

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;


}
