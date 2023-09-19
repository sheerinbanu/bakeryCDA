package com.ecommerce.bakery.Validator;

import java.util.regex.*;

public class EmailValidation {
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }
}

