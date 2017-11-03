package ua.com.tkachenko.onlinestore.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.tkachenko.onlinestore.model.Manufacturer;

public interface ManufacturerDao extends CrudRepository<Manufacturer, Long> {

    @Query("SELECT m FROM Manufacturer m LEFT JOIN FETCH m.goods WHERE m.id = :id")
    Manufacturer findById(@Param("id") long id);

    @Query("SELECT m FROM Manufacturer m LEFT JOIN FETCH m.goods WHERE m.name = :name")
    Manufacturer findByName(@Param("name") String name);
}
