package com.ecommerce.bakery.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="selection")
@Data
public class Selection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_selection;
    private int quantity;
    private double total;
    private int id_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", referencedColumnName= "id_product", insertable = true, updatable = true)
    private Product product;
}
