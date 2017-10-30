package ua.com.tkachenko.onlinestore.dao;

import org.springframework.data.repository.CrudRepository;
import ua.com.tkachenko.onlinestore.model.Manufacturer;

public interface ManufacturerDao extends CrudRepository<Manufacturer, Long> {
    Manufacturer findByName(String name);
}
