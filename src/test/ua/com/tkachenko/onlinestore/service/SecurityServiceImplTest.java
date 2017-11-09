package ua.com.tkachenko.onlinestore.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.tkachenko.onlinestore.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SecurityServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private SecurityServiceImpl securityService;

    @Before
    public void init () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void autoLogin() throws Exception {

        String username = "user";
        String password = "password";

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, password, authorities);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        assertEquals(userDetails, userDetailsService.loadUserByUsername(username));

        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authenticationToken);
        assertEquals(authenticationToken, authenticationManager.authenticate(authenticationToken));



    }
}