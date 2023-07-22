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

    private static final String EMAIL_REGEX ="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
    /*
    *  It allows numeric values from 0 to 9.
       Both uppercase and lowercase letters from a to z are allowed.
       Allowed are underscore “_”, hyphen “-“, and dot “.”
       Dot isn't allowed at the start and end of the local part.
       Consecutive dots aren't allowed.
       For the local part, a maximum of 64 characters are allowed
       * */
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }
    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "NotEmpty");
        if (user.getFirst_name().length() < 2 || user.getFirst_name().length() > 50) {
            errors.rejectValue("first_name", "Size.userForm.first_name");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "NotEmpty");
        if (user.getLast_name().length() < 2 || user.getLast_name().length() > 50) {
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
        if (!Pattern.matches(passwordPattern, user.getPassword())) {
            errors.rejectValue("password", "Pattern.userForm.password");
        }
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }



    }
}
