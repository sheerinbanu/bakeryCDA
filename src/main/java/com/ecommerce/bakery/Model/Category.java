package com.ecommerce.bakery.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_category;
    private String name;
    private String picture;
}
