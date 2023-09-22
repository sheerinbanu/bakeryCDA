package com.ecommerce.bakery.Controller;

import com.ecommerce.bakery.Model.Cart;
import com.ecommerce.bakery.Model.PaymentOrder;
import com.ecommerce.bakery.Service.PayPalService;
import com.ecommerce.bakery.Service.CartService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/paypal")
public class PayPalController {
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    @Autowired
    PayPalService payPalService;

    @GetMapping("/pay")
    public String payment() {
        return "payment";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/pay")
    public String payment(@ModelAttribute("Cart") Cart cart) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Authenticated user: " + authentication.getName());

            Payment payment = payPalService.createPayment(
                    cart,
                    "http://localhost:9090/" + CANCEL_URL,
                    "http://localhost:9090/" + SUCCESS_URL
            );

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace(); // This should be logged in a real application
        }

        return "redirect:/"; // Redirect to a proper page after payment failure
    }



    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "PayPalCancelPayment";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "PayPalSuccessPayment";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
