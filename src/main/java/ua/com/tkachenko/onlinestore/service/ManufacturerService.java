package ua.com.tkachenko.onlinestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.tkachenko.onlinestore.dao.ManufacturerDao;
import ua.com.tkachenko.onlinestore.model.Manufacturer;

import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerDao manufacturerDao;

    public Manufacturer save (Manufacturer manufacturer) {
        return manufacturerDao.save(manufacturer);
    }

    public Iterable<Manufacturer> findAll () {

       return manufacturerDao.findAll();
    }

    public Manufacturer findByName (String name) {
        return manufacturerDao.findByName(name);
    }

    public Manufacturer findById(long id) {
        return manufacturerDao.findOne(id);
    }

    public void remove (long id) {
        manufacturerDao.delete(id);
    }
}
