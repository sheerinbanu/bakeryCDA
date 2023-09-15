package com.ecommerce.bakery;

import com.ecommerce.bakery.Service.ProductService;
import com.ecommerce.bakery.Validator.EmailValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestsConfiguration {

    @Bean
    public EmailValidation emailValidation() {
        return new EmailValidation();
    }

}
