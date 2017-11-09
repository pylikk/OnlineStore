package ua.com.tkachenko.onlinestore.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.tkachenko.onlinestore.dao.ManufacturerDao;
import ua.com.tkachenko.onlinestore.model.Manufacturer;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ManufacturerServiceTest {

    @Mock
    private ManufacturerDao manufacturerDao;

    @InjectMocks
    private ManufacturerService manufacturerService;

    @Before
    public void init () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {

        Manufacturer manufacturer = new Manufacturer();

        when(manufacturerDao.save(manufacturer)).thenReturn(manufacturer);
        assertEquals(manufacturer, manufacturerService.save(manufacturer));
        verify(manufacturerDao).save(manufacturer);
    }

    @Test
    public void testFindAll() throws Exception {

        Iterable<Manufacturer> manufacturers = new ArrayList<>();
        when(manufacturerDao.findAll()).thenReturn(manufacturers);
        assertEquals(manufacturers, manufacturerService.findAll());
        verify(manufacturerDao).findAll();
    }

    @Test
    public void testFindByName() throws Exception {

        String name = anyString();
        Manufacturer manufacturer = new Manufacturer();

        when(manufacturerDao.findByName(name)).thenReturn(manufacturer);
        assertEquals(manufacturer, manufacturerService.findByName(name));
        verify(manufacturerDao).findByName(name);
    }

    @Test
    public void testFindById() throws Exception {

        Long id = 1L;
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(id);

        when(manufacturerDao.findById(id)).thenReturn(manufacturer);
        assertEquals(manufacturer, manufacturerService.findById(id));
        verify(manufacturerDao).findById(id);
    }

    @Test
    public void testRemove() throws Exception {

        Long id = anyLong();
        manufacturerService.remove(id);

        verify(manufacturerDao).delete(id);
    }
}