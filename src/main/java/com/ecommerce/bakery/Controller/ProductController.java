package com.ecommerce.bakery.Controller;

import com.ecommerce.bakery.Model.Product;
import com.ecommerce.bakery.Model.Selection;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductService ps;

    @GetMapping("/bakingPans")
    public String showProducts(Model model) {
        return ps.getListProduct(model);
    }


    @PostMapping("/bakingPans")
    public String addSelectToCart(Selection selection, User user, Product product, Authentication authentication, Model model, BindingResult bindingResult) {
        return ps.submitSelectionForm(selection, user, product, authentication, model, bindingResult);
    }


}
