package ua.com.tkachenko.onlinestore.dao;

import org.springframework.data.repository.CrudRepository;
import ua.com.tkachenko.onlinestore.model.Goods;

import java.util.List;

public interface GoodsDao extends CrudRepository<Goods, Long> {
    List<Goods> findByNameLike (String nameLike);
}
