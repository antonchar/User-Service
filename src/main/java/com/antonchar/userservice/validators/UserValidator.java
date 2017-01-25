package com.antonchar.userservice.validators;

import com.antonchar.userservice.services.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;

        if (user.getAge() != null && user.getAge() < 18 && user.isAdmin()) {
            errors.rejectValue("admin", "error.admin.young");
        }
    }
}
