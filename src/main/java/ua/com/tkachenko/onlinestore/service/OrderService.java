package ua.com.tkachenko.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.tkachenko.onlinestore.dao.OrderDao;
import ua.com.tkachenko.onlinestore.model.Order;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public Order save (Order order) {
        return orderDao.save(order);
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

    public List<String> statusList () {
        return orderDao.statusList();
    }
}
