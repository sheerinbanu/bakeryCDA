package com.ecommerce.bakery.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {
    @GetMapping("/")
    public String homePage(Model model)throws Exception {
        return "landingPage";
    }
}
