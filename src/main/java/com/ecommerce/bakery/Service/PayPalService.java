package com.ecommerce.bakery.Service;

import com.ecommerce.bakery.Model.Cart;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PayPalService {
    @Autowired
    private APIContext apiContext;

   public Payment createPayment(Cart cart, String cancelUrl, String successUrl) throws PayPalRESTException {
       // Retrieve necessary information from the Cart
       double totalAmount = cart.getTotal_price();
       String currency = "EUR";

       // Create PayPal Amount and Transaction
       Amount amount = new Amount();
       amount.setCurrency(currency);
       amount.setTotal(String.format("%.2f", totalAmount));

       Transaction transaction = new Transaction();
       transaction.setAmount(amount);

       // Step 3: Create PayPal Payment
       Payer payer = new Payer();
       payer.setPaymentMethod("paypal");

       Payment payment = new Payment();
       payment.setPayer(payer);
       payment.setTransactions(Collections.singletonList(transaction));
       payment.setIntent("sale");

       RedirectUrls redirectUrls = new RedirectUrls();
       redirectUrls.setCancelUrl(cancelUrl);
       redirectUrls.setReturnUrl(successUrl);
       payment.setRedirectUrls(redirectUrls);

       return payment.create(apiContext);
   }


    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        // The actual PayPal payment execution happens here
        return payment.execute(apiContext, paymentExecute);
    }



}
