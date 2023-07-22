package com.ecommerce.bakery.Controller;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Model model) {
        return "error";
    }
}

