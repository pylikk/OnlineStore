package ua.com.tkachenko.onlinestore.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.com.tkachenko.onlinestore.model.User;
import ua.com.tkachenko.onlinestore.service.SecurityService;
import ua.com.tkachenko.onlinestore.service.UserService;
import ua.com.tkachenko.onlinestore.validator.UserValidator;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MainControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private SecurityService securityService;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private MainController mainController;

    MockMvc mockMvc;

    @Before
    public void setup() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mainController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testRegistrationGet() throws Exception {

        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attribute("userForm", instanceOf(User.class)));
    }

    @Test
    public void testRegistrationPost() throws Exception {

        User user = new User();

        mockMvc.perform(post("/registration"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/cart"));

        verify(userService).save(user);
        verify(securityService).autoLogin(user.getUsername(), user.getConfirmPassword());
    }

    @Test
    public void testLogin() throws Exception {

        mockMvc.perform(get("/login")
            .param("error", "error")
            .param("logout", "logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("error", "Username or password is incorrect."))
                .andExpect(model().attribute("message", "Logged out successfully."));
    }

    @Test
    public void testAdmin() throws Exception {

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"));
    }

}