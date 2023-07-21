package com.ecommerce.bakery.Repository;

import com.ecommerce.bakery.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.bakery.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
   /* Optional<Product> findById(int categoryId);*/

    List<Product> findByCategory(Category category);
}
