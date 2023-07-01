package com.ecommerce.bakery.Service;

import com.ecommerce.bakery.Model.Category;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Repository.ProductRepository;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.bakery.Model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.ecommerce.bakery.Model.Selection;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
@Data
public class ProductService {
    @Autowired
    private ProductRepository pr;

    @Autowired
    private UserService us;

    @Autowired
    private SelectionService ss;
    static List<Integer> quantityList = null;
    static {
        quantityList = new ArrayList<>();
        quantityList.add(1);
        quantityList.add(2);
        quantityList.add(3);
        quantityList.add(4);
        quantityList.add(5);
    }
    Double unit_price;
    double finalPrice;


    public List<Product> getAllProduct() {
        return pr.findAll();
    }

    public void deleteProduct(final int id) {
        pr.deleteById(id);
    }

    public void insertProduct(Product Product) {
        pr.save(Product);
    }

    public String getListProduct(Model model) {
        model.addAttribute("products", getAllProduct());
        model.addAttribute("quantityList", quantityList);
        return "products";
    }

    public String submitSelectionForm(@ModelAttribute("selectionForm") Selection selection, User user, Product product, Authentication authentication, Model model, BindingResult bindingResult){
        int currentUserId = us.findByUsername(authentication.getName()).getId_user();
        finalPrice = (unit_price * selection.getQuantity());
        ss.insertSelection(selection);
        return "redirect:/products";
    }

    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> allProducts = getAllProduct();
        List<Product> categoryProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.getCategory().getId_category() == categoryId) {
                categoryProducts.add(product);
            }
        }

        return categoryProducts;
    }



}




