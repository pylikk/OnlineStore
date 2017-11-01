package ua.com.tkachenko.onlinestore.dao;

import org.springframework.data.repository.CrudRepository;
import ua.com.tkachenko.onlinestore.model.Order;

public interface OrderDao extends CrudRepository<Order, Long> {
}
