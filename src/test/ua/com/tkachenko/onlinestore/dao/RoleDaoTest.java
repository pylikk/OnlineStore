package ua.com.tkachenko.onlinestore.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.tkachenko.onlinestore.model.Role;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class RoleDaoTest {

    @Autowired
    private RoleDao roleDao;

    @Before
    public void init () {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_TEST");
        roleDao.save(role);
    }

    @Test
    public void testFindOne () {

        assertEquals("ROLE_TEST", roleDao.findOne(1L).getName());
    }

}