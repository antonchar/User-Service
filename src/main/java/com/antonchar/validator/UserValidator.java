package com.antonchar.validator;


import com.antonchar.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getAge() != null && user.getAge() < 18 && user.getIsAdmin()) {
            errors.rejectValue("isAdmin", "admin.young", "Admin cannot be younger then 18 years old");
        }
    }
}
