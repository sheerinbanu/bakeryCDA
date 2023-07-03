package com.ecommerce.bakery.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "cart")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cart;
    private Double total_price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName= "id_user", insertable = true, updatable = false)
    private User user;
}
