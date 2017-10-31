package ua.com.tkachenko.onlinestore.dao;

import org.springframework.data.repository.CrudRepository;
import ua.com.tkachenko.onlinestore.model.Goods;

public interface GoodsDao extends CrudRepository<Goods, Long> {

}
