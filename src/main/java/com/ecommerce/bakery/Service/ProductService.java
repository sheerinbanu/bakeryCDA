package com.ecommerce.bakery.Service;

import com.ecommerce.bakery.Model.Category;
import com.ecommerce.bakery.Model.Product;
import com.ecommerce.bakery.Model.Selection;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Repository.CategoryRepository;
import com.ecommerce.bakery.Repository.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ProductService {
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
    @Autowired
    private ProductRepository pr;
    @Autowired
    private UserService us;
    @Autowired
    private SelectionService ss;
    @Autowired
    private CategoryRepository cr;


    public Optional<Product> getProduct(final int id){
        return pr.findById(id);
    }
    public Iterable<Product> getAllProduct() {
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

    /*public List<Product> getProductsByCategory(int categoryId) {
        Iterable<Product> allProducts = getAllProduct();
        List<Product> categoryProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getCategory().getId_category() == categoryId) {
                categoryProducts.add(product);
            }
        }
        return categoryProducts;
    }*/
    public List<Product> getProductsByCategoryId(int categoryId) {
        // First, find the category by its ID
        Category category = cr.findById(categoryId).orElse(null);
        if (category == null) {
            // If the category doesn't exist, you might want to handle this situation accordingly.
            // For example, you can throw an exception or return an empty list.
            return Collections.emptyList();
        }
        // If the category exists, fetch its related products
        List<Product> products = pr.findByCategory(category);
        return products;
    }

    public String submitSelectionForm(@ModelAttribute("selectionForm") Selection selection, User user, Product product, Authentication authentication, Model model, BindingResult bindingResult) {
        int currentUserId = us.findByUsername(authentication.getName()).getId_user();
        if (product == null) {
            // Handle the case where the product is null (you can return an error message, redirect, or perform other appropriate actions)
            return "redirect:/errorPage";
        }
        double finalPrice = (product.getUnit_price() * selection.getQuantity());
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        finalPrice = Double.parseDouble(decimalFormat.format(finalPrice));
        selection.setTotal(finalPrice);
        selection.setId_user(currentUserId);
        ss.insertSelection(selection);
        return "redirect:/productGeneralPage";
    }
}




