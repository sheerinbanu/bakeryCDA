package com.ecommerce.bakery.Controller;

import com.ecommerce.bakery.Model.Category;
import com.ecommerce.bakery.Model.Order;
import com.ecommerce.bakery.Model.Product;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Repository.OrderRepository;
import com.ecommerce.bakery.Service.*;
import com.ecommerce.bakery.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService ps;
    @Autowired
    private CategoryService cs;
    @Autowired
    private OrderService os;
    @Autowired
    private OrderRepository or;

    @GetMapping("/admin/addAdmin")
    public String admin(@ModelAttribute("userForm") User userForm) {
        return "adminPage";
    }

    @PostMapping("/admin/addAdmin")
    public String admin(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "adminPage";
        }
        authService.createNewAdmin(userForm);
        model.addAttribute("success_message", "New admin account successfully created");
        model.addAttribute("has_success", true);
        return "adminPage";
    }
    @GetMapping("/admin/manageProduct")
    public String ManageController(Model model) {
        model.addAttribute("products", ps.getAllProduct());
        return "manageProductPage";
    }
    @GetMapping("/admin/addProduct")
    public String AddProduct(Model model, @ModelAttribute("product") Product product) throws Exception {
        model.addAttribute("categories", cs.getAllCategory());
        return "addProductPage";
    }

    @PostMapping("/admin/addProduct")
    public String RegisterProduct(@ModelAttribute("product") Product product) throws Exception {
        ps.insertProduct(product);
        return "redirect:/admin/manageProduct";
    }

    @GetMapping("/admin/deleteProduct/{id}")
    public String DeleteProduct(@PathVariable(name = "id") int id) {
        ps.deleteProduct(id);
        return "redirect:/admin/manageProduct";
    }

    @GetMapping("/admin/updateProduct/{id}")
    public String UpdateProduct(Model model, @ModelAttribute("product") Product product, @PathVariable(name = "id") int productId, BindingResult bindingResult) {
        Optional<Product> product1 = ps.getProduct(productId);
        if (product1.isPresent()) {
            Product product2 = product1.get();
            model.addAttribute("product", product1);
        } else {
            return "error";
        }
        model.addAttribute("categories", cs.getAllCategory());
        return "addProductPage";
    }
    @GetMapping("/admin/manageCategory")
    public String ManageCategory(Model model){
        model.addAttribute("categories", cs.getAllCategory());
        return "manageCategoryPage";
    }
    @GetMapping("/admin/addCategory")
    public String AddCategory(Model model, @ModelAttribute("category")Category category){
        return "addCategoryPage";
    }
    @PostMapping("/admin/addCategory")
    public String RegisterCategory(@ModelAttribute("category") Category category){
        cs.insertCategory(category);
        return "redirect:/admin/manageCategory";
    }
    @GetMapping("/admin/deleteCategory/{id}")
    public String DeleteCategory(@PathVariable(name = "id") int id) {
        cs.deleteCategory(id);
        return "redirect:/admin/manageCategory";
    }
    @GetMapping("/admin/updateCategory/{id}")
    public String updateCategory(Model model, @PathVariable("id") int categoryId) {
        Optional<Category> categoryOptional = cs.getCategory(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            model.addAttribute("category", category);
        } else {
            // Handle the case when the category is not found
            // You can redirect to an error page
            return "error";
        }
        return "addCategoryPage";
    }
    @GetMapping({"/admin/manageUser"})
    public String getAllUsersProfile(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "manageUserPage";
    }
    @GetMapping("/admin/updateUser/{id}")
    public String getUser(Model model, @ModelAttribute("user")User user, @PathVariable(name = "id")  int id_user, BindingResult bindingResult){
        Optional<User> user1 = userService.getUser(id_user);
        model.addAttribute("user", user1);
        return "updateUserPage";
    }
    @PostMapping("/admin/updateUser/{id}")
   public String updateUser(@PathVariable("id") int id, @ModelAttribute User user) {
       user.setId(id); // Set the user ID from the path variable
       userService.updateUser(user);
       userService.insertUser(user);
       return "redirect:/admin/manageUser";
   }
    @GetMapping("/admin/deleteUser/{id}")
    public String DeleteUser(@PathVariable(name = "id") int id) {
        userService.removeUser(id);
        return "redirect:/admin/manageUser";
    }
    @GetMapping("/admin/orders")
    public String ShowOrders(@ModelAttribute("orderForm") Order order, Model model) {
        model.addAttribute("orders", os.getAllOrder());
        return "adminOrdersPage";
    }
    @GetMapping("/admin/orders/{id}")
    public String getSelectionByOrder(Model model, @ModelAttribute("selection") Order order, @PathVariable(name = "id") int Id_order){
        os.getDetailSelectionById(Id_order, model);
        return "adminOrdersDetailsPage";
    }
    @PostMapping("/admin/orders")
    public String submitCartForm(@RequestParam("orderId") int orderId, @RequestParam("orderDate") String orderDate, @ModelAttribute("orderForm") Order order, Model model) {
        order.setId_order(orderId);
        order.setOrder_date(orderDate);
        order.setValidate(true);
        or.save(order);
        return "redirect:/admin/orders";
    }
}
