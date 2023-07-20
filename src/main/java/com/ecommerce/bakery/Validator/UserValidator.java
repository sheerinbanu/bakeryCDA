package com.ecommerce.bakery.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Service.UserService;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    private static final String EMAIL_REGEX ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }
    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "NotEmpty");
        if (user.getFirst_name().length() < 2 || user.getFirst_name().length() > 20) {
            errors.rejectValue("first_name", "Size.userForm.first_name");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "NotEmpty");
        if (user.getLast_name().length() < 2 || user.getLast_name().length() > 20) {
            errors.rejectValue("last_name", "Size.userForm.last_name");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail() == null ||!user.getEmail().matches(EMAIL_REGEX)) {
            errors.rejectValue("email", "Pattern.userForm.email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }



    }
}
