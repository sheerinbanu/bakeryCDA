package com.ecommerce.bakery.Repository;

import com.ecommerce.bakery.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
