package com.antonchar.userservice.validation;

import com.antonchar.userservice.entity.User;
import com.antonchar.userservice.service.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.antonchar.userservice.validation.ValidationConstants.ADMIN_MIN_AGE;
import static com.antonchar.userservice.validation.ValidationConstants.YOUNG_ADMIN_ERR_CODE;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        if (isAdmin(user) && user.getAge() < ADMIN_MIN_AGE) {
            errors.rejectValue("role", YOUNG_ADMIN_ERR_CODE,
                new String[] {String.valueOf(user.getRole()), String.valueOf(ADMIN_MIN_AGE)}, null);
        }
    }

    private boolean isAdmin(UserDto user) {
        return user.getRole() == User.Role.ADMIN || user.getRole() == User.Role.SUPERADMIN;
    }
}
