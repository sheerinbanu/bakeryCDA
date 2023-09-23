package com.ecommerce.bakery.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentOrder {
    private double price;
    private String currency;
    private String method;
    private Cart cart;
}
