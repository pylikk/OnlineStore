package ua.com.tkachenko.onlinestore.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tkachenko.onlinestore.model.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderDao extends CrudRepository<Order, Long> {

    default List<String> statusList () {

        List<String> statusList = new ArrayList<>();
        statusList.add("new");
        statusList.add("processed");
        statusList.add("completed");

        return statusList;
    }
}
