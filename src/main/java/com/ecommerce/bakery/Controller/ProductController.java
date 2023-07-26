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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService ps;
    @Autowired
    private SelectionService ss;
    @GetMapping("/showProductsByCategory/{categoryId}")
    public String showProductsByCategory(@PathVariable int categoryId, Model model) throws Exception {
        List<Product> products = ps.getProductsByCategoryId(categoryId);
        model.addAttribute("products", products);
        return "productsPage";
    }
    @PostMapping("/showProductsByCategory/{categoryId}")
    public String addSelectToCart(Selection selection, User user, Product product, Authentication authentication, Model model, BindingResult bindingResult, @PathVariable String categoryId) throws Exception {
        return ps.submitSelectionForm(selection, user, product, authentication, model, bindingResult);
    }
}
