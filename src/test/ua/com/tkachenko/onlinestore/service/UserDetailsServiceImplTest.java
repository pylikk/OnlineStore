package ua.com.tkachenko.onlinestore.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.tkachenko.onlinestore.dao.UserDao;
import ua.com.tkachenko.onlinestore.model.Role;
import ua.com.tkachenko.onlinestore.model.User;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Before
    public void init () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadUserByUsername() throws Exception {

        String username = "user";
        String password = "password";
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setConfirmPassword(password);
        user.setRoles(roles);

        when(userDao.findByUsername(username)).thenReturn(user);

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        verify(userDao).findByUsername(username);

        Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) userDetails.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals(true, authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertEquals(false, authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

}