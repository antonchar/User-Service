package com.antonchar.userservice.util;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.antonchar.userservice.entities.User;
import com.antonchar.userservice.services.dto.UserDto;

import static com.antonchar.userservice.util.ValidationConstants.ADMIN_MIN_AGE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserValidatorTest {

    private UserValidator userValidator;

    @Before
    public void setUp() throws Exception {
        userValidator = new UserValidator();
    }

    @Test
    public void testValidUserDto() throws Exception {
        UserDto validUser = new UserDto()
            .setAge(ADMIN_MIN_AGE)
            .setRole(User.Role.ADMIN);

        Errors errors = new BeanPropertyBindingResult(validUser, "validUser");
        userValidator.validate(validUser, errors);

        assertThat(errors.hasErrors(), is(false));
    }

    @Test
    public void testInvalidYoungAdminUserDto() throws Exception {
        UserDto invalidUser = new UserDto()
            .setAge(ADMIN_MIN_AGE - 1)
            .setRole(User.Role.ADMIN);

        Errors errors = new BeanPropertyBindingResult(invalidUser, "invalidUser");
        userValidator.validate(invalidUser, errors);

        assertThat(errors.getErrorCount(), is(1));
        assertThat(errors.getFieldError("role"), is(notNullValue()));
    }
}