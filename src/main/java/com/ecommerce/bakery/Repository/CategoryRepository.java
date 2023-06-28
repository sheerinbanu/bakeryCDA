package com.ecommerce.bakery.Repository;

import com.ecommerce.bakery.Model.Role;
import com.ecommerce.bakery.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.bakery.Model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();
}
