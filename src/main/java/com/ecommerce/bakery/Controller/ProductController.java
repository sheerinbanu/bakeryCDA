package com.ecommerce.bakery.Controller;

import com.ecommerce.bakery.Model.Product;
import com.ecommerce.bakery.Model.Selection;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Service.ProductService;
import com.ecommerce.bakery.Service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    static List<Integer> quantityList = null;

    static {
        quantityList = new ArrayList<>();
        quantityList.add(1);
        quantityList.add(2);
        quantityList.add(3);
        quantityList.add(4);
        quantityList.add(5);
    }
    @Autowired
    ProductService ps;
    @Autowired
    private SelectionService ss;
    @GetMapping("/bakingPans")
    public String showProductsByCategory1(Model model) {
        List<Product> products = ps.getProductsByCategory(1);
        model.addAttribute("products", products);
        model.addAttribute("quantityList", quantityList);
        return "bakingPansPage";
    }
    @PostMapping("/bakingPans")
    public String addSelectToCart1(Selection selection, User user, Product product, Authentication authentication, Model model, BindingResult bindingResult) {
        return ps.submitSelectionForm(selection, user, product, authentication, model, bindingResult);
    }
    @GetMapping("/foodDecoration")
    public String getProductsByCategory2(Model model) {
        List<Product> products = ps.getProductsByCategory(2);
        model.addAttribute("products", products);
        model.addAttribute("quantityList", quantityList);
        return "foodDecorationPage";
    }

    @PostMapping("/foodDecoration")
    public String addSelectToCart2(Selection selection, User user, Product product, Authentication authentication, Model model, BindingResult bindingResult) {
        return ps.submitSelectionForm(selection, user, product, authentication, model, bindingResult);
    }
    @GetMapping("/chocolates")
    public String getProductsByCategory3(Model model) {
        List<Product> products = ps.getProductsByCategory(3);
        model.addAttribute("products", products);
        model.addAttribute("quantityList", quantityList);
        return "chocolatesPage";
    }
    @PostMapping("/chocolates")
    public String addSelectToCart3(Selection selection, User user, Product product, Authentication authentication, Model model, BindingResult bindingResult) {
        return ps.submitSelectionForm(selection, user, product, authentication, model, bindingResult);
    }
}
