package com.ecommerce.bakery.Controller;

import com.ecommerce.bakery.Model.Cart;
import com.ecommerce.bakery.Model.Order;
import com.ecommerce.bakery.Model.Selection;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
    @Autowired
    private CartService cs;
    @GetMapping({"/cart"})
    public String showCart(Model model, User user, Authentication authentication) throws Exception {
        return cs.getSelection(model, user, authentication);
    }

    @PostMapping("/cart")
    public String createOrder(Cart cart, Order order, Selection selection, User user, Authentication authentication, Model model, BindingResult bindingResult) {
        return cs.submitCartForm(cart, order, selection, user, authentication, model, bindingResult);
    }
}
