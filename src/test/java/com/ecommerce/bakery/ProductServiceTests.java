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
        // Creating a new Category object and setting its properties
        Category testCategory = new Category();
        testCategory.setName("anniversary");
        testCategory.setPicture("category.jpg");

        // Save the Category object to the database
        testCategory = categoryRepository.save(testCategory);

        // Check if the save operation was successful
        if (testCategory != null) {
            System.out.println("Category saved successfully. ID: " + testCategory.getId_category());

            // Creating and saving Product objects
            Product product1 = new Product();
            product1.setId_product(1);
            product1.setName("Lorum ipsum");
            product1.setDescription("Lorum Ipsum");
            product1.setUnit_price(22.02);
            product1.setPicture("product1.jpg");

            Product product2 = new Product();
            product2.setId_product(2);
            product2.setName("Lorum ipsum");
            product2.setDescription("Lorum Ipsum");
            product2.setUnit_price(10.09);
            product2.setPicture("product2.jpg");

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
        // Setting up a mock CategoryRepository that returns Optional.empty()
        Mockito.when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        // Calling the method under test with any category ID
        List<Product> products = productService.getProductsByCategoryId(99);

        // Asserting that an empty list is returned when the category is null
        Assertions.assertEquals(Collections.emptyList(), products);
    }

}
