package com.ecommerce.bakery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.bakery.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
