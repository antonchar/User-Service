package com.antonchar.validators;


import com.antonchar.entities.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getAge() != null && user.getAge() < 18 && user.isAdmin()) {
            errors.rejectValue("admin", "error.admin.young", "Admin cannot be younger than 18 years old");
        }
    }
}
