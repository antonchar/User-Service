package com.antonchar.userservice.util;

import com.antonchar.userservice.entities.User;
import com.antonchar.userservice.services.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final static int ADMIN_MIN_AGE = 18;

    @Override
    public boolean supports(Class clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        if (isAdmin(user) && user.getAge() < ADMIN_MIN_AGE) {
            errors.rejectValue("admin", "error.admin.young",
                new String[] {String.valueOf(user.getRole()), String.valueOf(ADMIN_MIN_AGE)}, null);
        }
    }

    private boolean isAdmin(UserDto user) {
        return user.getRole() == User.Role.ADMIN || user.getRole() == User.Role.SUPERADMIN;
    }
}
