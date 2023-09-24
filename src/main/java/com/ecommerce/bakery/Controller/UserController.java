package com.ecommerce.bakery.Controller;

import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Service.AuthService;
import com.ecommerce.bakery.Service.UserService;
import com.ecommerce.bakery.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) throws Exception {
        model.addAttribute("userForm", new User());
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,Model model) throws Exception {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registrationPage";
        }
        authService.createNewUser(userForm);
        model.addAttribute("message", "Congratulations your registration was successful!");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "loginPage";
    }
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgotPasswordPage";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "loginPage";
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        model.addAttribute("person", userService.findByUsername(authentication.getName()));
        return "profilePage";
    }
}
