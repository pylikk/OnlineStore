package ua.com.tkachenko.onlinestore.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.tkachenko.onlinestore.dao.RoleDao;
import ua.com.tkachenko.onlinestore.dao.UserDao;
import ua.com.tkachenko.onlinestore.model.User;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void init () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {

        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        userService.save(user);

        verify(userDao).save(user);
        verify(roleDao).getOne(1L);
        verify(encoder).encode("password");
        assertNotEquals("password", user.getPassword());
    }

    @Test
    public void testFindByUsername() throws Exception {

        String username = anyString();
        User user = new User();

        when(userDao.findByUsername(username)).thenReturn(user);
        User result = userService.findByUsername(username);

        verify(userDao).findByUsername(username);
        assertEquals(result, user);
    }
}