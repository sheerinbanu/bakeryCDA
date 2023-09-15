package com.ecommerce.bakery.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(emailAddress);
        System.out.println("Matching occurrences:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        return false;
    }
}
