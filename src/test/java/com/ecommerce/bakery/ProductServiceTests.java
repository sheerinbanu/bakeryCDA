package com.ecommerce.bakery;

import com.ecommerce.bakery.Model.Category;
import com.ecommerce.bakery.Model.Product;
import com.ecommerce.bakery.Repository.CategoryRepository;
import com.ecommerce.bakery.Repository.ProductRepository;
import com.ecommerce.bakery.Service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class ProductServiceTests {

    @Autowired
    private ProductService productService;
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetProductsByCategoryIdWhenCategoryExists() {
        // Create a new Category object and set its properties
        Category testCategory = new Category();
        testCategory.setName("Spatulas");
        testCategory.setPicture("category.jpg");

        // Save the Category object to the database
        testCategory = categoryRepository.save(testCategory);

        // Check if the save operation was successful
        if (testCategory != null) {
            System.out.println("Category saved successfully. ID: " + testCategory.getId_category());

            // Create and save Product objects
            Product product1 = new Product();
            Product product2 = new Product();

            // Set the Category for the products
            product1.setCategory(testCategory);
            product2.setCategory(testCategory);

            productRepository.save(product1);
            productRepository.save(product2);

            //Calling the method to test
            List<Product> products = productService.getProductsByCategoryId(testCategory.getId_category());

            Assertions.assertEquals(2, products.size());
        } else {
            System.out.println("Error saving Category.");
        }
    }


    @Test
    public void testGetProductsByCategoryIdWhenCategoryDoesNotExist() {
        List<Product> products = productService.getProductsByCategoryId(-1);
        Assertions.assertEquals(Collections.emptyList(), products);
    }

    @Test
    public void testGetProductsByCategoryIdWhenCategoryIsNull() {
        // Set up a mock CategoryRepository that returns Optional.empty()
        // This will simulate the case when the repository returns null
        Mockito.when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // Call the method under test with any category ID
        List<Product> products = productService.getProductsByCategoryId(1);

        // Assert that an empty list is returned when the category is null
        Assertions.assertEquals(Collections.emptyList(), products);
    }
}
