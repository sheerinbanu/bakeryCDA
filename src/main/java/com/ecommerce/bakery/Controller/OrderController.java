package com.ecommerce.bakery.Controller;

import com.ecommerce.bakery.Model.Order;
import com.ecommerce.bakery.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {
   @Autowired
    private OrderService os;

    @GetMapping({"/orderHistory"})
    public String orderHistory(Model model, Authentication authentication) {
        return os.getAllOrdersByUser(authentication, model);
    }
    @GetMapping("/orderHistory/{id}")
    public String getSelectionByOrder(Model model, @ModelAttribute("selection") Order order, @PathVariable(name = "id") int Id_order){
        os.getDetailSelectionById(Id_order, model);
        return "orderDetailsPage";
    }
}
