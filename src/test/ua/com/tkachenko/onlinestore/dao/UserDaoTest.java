package ua.com.tkachenko.onlinestore.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tkachenko.onlinestore.model.Role;
import ua.com.tkachenko.onlinestore.model.User;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Before
    public void init () {
        Role role = new Role();
        role.setName("ROLE_TEST");
        role.setId(1L);
        roleDao.save(role);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setId(1L);
        user.setUsername("user");
        user.setPassword("password");
        user.setConfirmPassword("password");
        user.setRoles(roles);

        userDao.save(user);
    }

    @Test
    @Transactional
    public void testSave () {

        Role role = roleDao.findOne(1L);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setId(2L);
        user.setUsername("user2");
        user.setPassword("password2");
        user.setConfirmPassword("password2");
        user.setRoles(roles);

        userDao.save(user);

        assertEquals(2, userDao.findAll().size());
        assertEquals("password2", userDao.findOne(2L).getPassword());
    }

    @Test
    public void testFindByUsername() throws Exception {

        User user = userDao.findByUsername("user");
        assertEquals("password", user.getPassword());
    }
}