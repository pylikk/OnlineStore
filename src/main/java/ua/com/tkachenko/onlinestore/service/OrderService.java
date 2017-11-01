package ua.com.tkachenko.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.tkachenko.onlinestore.dao.OrderDao;
import ua.com.tkachenko.onlinestore.model.Order;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public void save (Order order) {
        orderDao.save(order);
    }

    public Iterable<Order> allOrders () {
        return orderDao.findAll();
    }

    public void remove (long id) {
        orderDao.delete(id);
    }

    public Order findOrderById (long id) {
        return orderDao.findOne(id);
    }
}
