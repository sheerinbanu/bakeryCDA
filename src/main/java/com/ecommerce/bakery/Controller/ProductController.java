package com.ecommerce.bakery.Controller;

import com.ecommerce.bakery.Model.Product;


import com.ecommerce.bakery.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService ps;

    @GetMapping("/bakingPans")
    public String getProductsByCategory1(Model model) {
        List<Product> products = ps.getProductsByCategory(1);
        model.addAttribute("products", products);
        return "bakingPansPage";

    }

@GetMapping("/foodDecoration")
    public String getProductsByCategory2(Model model) {
        List<Product> products = ps.getProductsByCategory(2);
        model.addAttribute("products", products);
        return "foodDecorationPage";
    }
    @GetMapping("/chocolates")
    public String getProductsByCategory3(Model model) {
        List<Product> products = ps.getProductsByCategory(3);
        model.addAttribute("products", products);
        return "chocolatesPage";
    }





}
