package com.ecommerce.bakery.Service;

import com.ecommerce.bakery.Model.Cart;
import com.ecommerce.bakery.Model.Order;
import com.ecommerce.bakery.Model.Selection;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Repository.CartRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@Data
@Service
public class CartService {
    @Autowired
    private UserService us;

    @Autowired
    private SelectionService ss;

    @Autowired
    private OrderService os;

    @Autowired
    private CartRepository cr;

    @Autowired
    private DataSource dataSource;

    public Optional<Cart> getCart(final int id) {
        return cr.findById(id);
    }

    public Iterable<Cart> getAllCart() {
        return cr.findAll();
    }

    public void deleteCart(final int id) {
        cr.deleteById(id);
    }

    public void insertCart(Cart Cart) {
        cr.save(Cart);
    }


    public String getSelection(Model model, User user, Authentication authentication) throws Exception {
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT SUM(total) FROM selection WHERE id_cart IS NULL";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            double sum = 0;
            if (resultSet.next()) {
                sum = resultSet.getDouble(1);
                System.out.println(sum);
            }

            // Format sum to two decimal places
            DecimalFormat df = new DecimalFormat("#.##");
            sum = Double.parseDouble(df.format(sum));

            int currentUserId = us.findByUsername(authentication.getName()).getId_user();
            ArrayList<Selection> list = new ArrayList<>();
            for (Selection s : ss.getAllSelection()) {
                if (s.getCart() == null && s.getId_user() == currentUserId) {
                    list.add(s);
                }
            }
            model.addAttribute("selections", list);
            model.addAttribute("person", us.findByUsername(authentication.getName()));
            model.addAttribute("finalPrice", sum);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return "cartPage";
    }


    public String submitCartForm(@ModelAttribute("cartForm") Cart cart, Order order, Selection selection, User user, Authentication authentication, Model model, BindingResult bindingResult) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        int currentUserId = us.findByUsername(authentication.getName()).getId_user();
        cart.setUser(user);
        insertCart(cart);
        for (Selection s : ss.getAllSelection()) {
            if (s.getCart() == null && s.getId_user() == currentUserId) {
                s.setCart(cart);
                ss.insertSelection(s);
            }
        }
        order.setCart(cart);
        order.setOrder_date(formattedDate);
        order.setUser(user);
        order.setValidate(false);
        os.insertOrder(order);
        return "redirect:/cart";
    }
}
