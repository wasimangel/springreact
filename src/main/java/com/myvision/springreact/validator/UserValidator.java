package com.myvision.springreact.validator;

import com.myvision.springreact.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "Legnth", "Password must be at least 6 char Long");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password", "confirmPassword", "Password did not matched");
        }
    }
}
