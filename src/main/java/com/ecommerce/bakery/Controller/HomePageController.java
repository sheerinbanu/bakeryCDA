package com.ecommerce.bakery.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String homePage(Model model)throws Exception {
        return "homePage";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) throws Exception{
        return "aboutPage";
    }

    @GetMapping("/recipes")
    public String recipes(Model model) throws Exception{
        return "recipesPage";
    }

    @GetMapping("/contact")
    public String contact(Model model) throws Exception{
        return "contactPage";
    }
}
