package ua.com.tkachenko.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.tkachenko.onlinestore.dao.GoodsDao;
import ua.com.tkachenko.onlinestore.model.Goods;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public Goods findGoodsById(long id) {
        return goodsDao.findOne(id);
    }

    public Goods save(Goods goods, MultipartFile image) {

        if (image.isEmpty()) {
            return goodsDao.save(goods);
        }

        Goods savedGoods = goodsDao.save(goods);
//
//        String fileName = "C:/Users/Ьф/IdeaProjects/OnlineStore/src/main/webapp/resources/images/" + savedGoods.getId() + ".jpg";
//
//        savedGoods.setImage(fileName);
//
//        File uploadedFile = new File(fileName);
//        try {
//            byte[] bytes = image.getBytes();
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
//            stream.write(bytes);
//            stream.flush();
//            stream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return goodsDao.save(savedGoods);
    }

    public void remove(long id) {
        goodsDao.delete(id);
    }

    public List<Goods> search(String search) {
        return goodsDao.findByNameLike("%" + search + "%");
    }

    public List<Goods> startedGoods() {
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
