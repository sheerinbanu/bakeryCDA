package com.ecommerce.bakery;

import com.ecommerce.bakery.Validator.EmailValidation;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

//@SpringJUnitConfig
@Configuration
public class EmailValidationTests {
    @Bean
    public EmailValidation emailValidation() {
        return new EmailValidation();
    }
   /* @Test
    public  void testUsingStrictRegex1() {
        String emailAddress = "username@xyz.com";
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        assertTrue(EmailValidation.patternMatches(emailAddress, regexPattern));
    }*/

   /* @Test
    public  void testUsingStrictRegex2() {
        String emailAddress = "username@xyz.com";
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        assertTrue(EmailValidation.patternMatches(emailAddress, regexPattern));
    }*/
   @Test
   public void testGmailSpecialCase() {
       String emailAddress = "username+something@domain.com";
       String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
               + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
       assertFalse(EmailValidation.patternMatches(emailAddress, regexPattern));
   }
   /* @Test
    public void testGmailSpecialCase2() {
        String emailAddress = "username@.com";
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
        assertTrue(EmailValidation.patternMatches(emailAddress, regexPattern));
    }*/

}
