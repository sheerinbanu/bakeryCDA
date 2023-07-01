package com.ecommerce.bakery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.bakery.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findById(int categoryId);


}
