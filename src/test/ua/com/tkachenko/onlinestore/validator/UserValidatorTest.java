package ua.com.tkachenko.onlinestore.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import ua.com.tkachenko.onlinestore.model.Goods;
import ua.com.tkachenko.onlinestore.model.User;
import ua.com.tkachenko.onlinestore.service.UserService;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserValidator userValidator;

    @Before
    public void inti () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSupports() throws Exception {

        User user = new User();

        assertTrue(userValidator.supports(user.getClass()));
        assertFalse(userValidator.supports(Goods.class));
    }

    @Test
    public void testValidate() throws Exception {

        User user = new User();
        user.setUsername("ss"); //  must be > 3
        user.setPassword("aaa"); // must be > 4
        user.setConfirmPassword("bbb");

        Errors errors = new BeanPropertyBindingResult(user, "user");

        userValidator.validate(user, errors);

        List<ObjectError> allErrors = errors.getAllErrors();

        assertTrue(allErrors.size() == 3);
    }
}