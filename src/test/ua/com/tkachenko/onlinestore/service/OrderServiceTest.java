package ua.com.tkachenko.onlinestore.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.tkachenko.onlinestore.dao.OrderDao;
import ua.com.tkachenko.onlinestore.model.Order;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    private OrderDao orderDao;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void init () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {

        Order order = new Order();
        when(orderDao.save(order)).thenReturn(order);
        assertEquals(order, orderService.save(order));
        verify(orderDao).save(order);
    }

    @Test
    public void testAllOrders() throws Exception {

        Iterable<Order> allOrders = new ArrayList<>();
        when(orderDao.findAll()).thenReturn(allOrders);
        assertEquals(allOrders, orderService.allOrders());
        verify(orderDao).findAll();
    }

    @Test
    public void testRemove() throws Exception {

        Long id = anyLong();
        orderService.remove(id);
        verify(orderDao).delete(id);
    }

    @Test
    public void testFindOrderById() throws Exception {

        Order order = new Order();
        when(orderDao.save(order)).thenReturn(order);
        assertEquals(order, orderService.save(order));
        verify(orderDao).save(order);
    }

    @Test
    public void testStatusList() throws Exception {

        List<String> statusList = new ArrayList<>();
        statusList.add("new");
        statusList.add("processed");
        statusList.add("completed");

        when(orderDao.statusList()).thenReturn(statusList);
        assertEquals(statusList, orderService.statusList());
        verify(orderDao).statusList();
    }

}