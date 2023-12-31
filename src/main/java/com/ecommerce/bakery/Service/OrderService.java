package com.ecommerce.bakery.Service;

import com.ecommerce.bakery.Model.Order;
import com.ecommerce.bakery.Model.Selection;
import com.ecommerce.bakery.Repository.OrderRepository;
import com.ecommerce.bakery.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Optional;



@Service
public class OrderService {

    @Autowired
    private OrderRepository or;

    @Autowired
    private UserRepository ur;

    @Autowired
    private SelectionService ss;

    @Autowired
    private UserService us;

    public Optional<Order> getOrder(final int id) {
        return or.findById(id);
    }

    public Iterable<Order> getAllOrder() {
        return or.findAll();
    }

    public void deleteOrder(final int id) {
        or.deleteById(id);
    }

    public void insertOrder(Order Order) {
        or.save(Order);
    }

    public String getAllOrdersByUser(Authentication authentication, Model model) {
        int currentUserId = us.findByUsername(authentication.getName()).getId_user();
        ArrayList<Order> orderList = new ArrayList<Order>();
        for (Order order : this.getAllOrder()) {
            if (order.getUser().getId_user() == currentUserId) {
                orderList.add(order);
            }
        }
        model.addAttribute("orders", orderList);
        return "orderPage";
    }

    public void getDetailSelectionById(int Id_order, Model model) {
        ArrayList<Selection> detailsList = new ArrayList<Selection>();
        for (Selection selection : ss.getAllSelection()) {
            if (selection.getCart() != null && (selection.getCart().getId_cart() == Id_order)) {
                detailsList.add(selection);
            }
        }
        model.addAttribute("details", detailsList);
    }
}
