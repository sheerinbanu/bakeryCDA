package com.ecommerce.bakery.Repository;

import com.ecommerce.bakery.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
