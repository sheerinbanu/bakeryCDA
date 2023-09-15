package com.ecommerce.bakery;

import com.ecommerce.bakery.Model.Category;
import com.ecommerce.bakery.Model.Product;
import com.ecommerce.bakery.Repository.CategoryRepository;
import com.ecommerce.bakery.Repository.ProductRepository;
import com.ecommerce.bakery.Service.ProductService;
import com.ecommerce.bakery.Service.UserService;
import com.ecommerce.bakery.Validator.EmailValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

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
        Category testCategory = new Category();
        testCategory.setName("Spatulas");
        testCategory.setPicture("category.jpg");
        testCategory = categoryRepository.save(testCategory);
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setDescription("This is the first product");
        product1.setUnit_price(19.99);
        product1.setPicture("product1.jpg");
        product1.setCategory(testCategory);
        productRepository.save(product1);

        Product product2 = new Product(/* Set product properties */);
        product1.setName("Product 2");
        product1.setDescription("This is the second product");
        product1.setUnit_price(26.77);
        product1.setPicture("product2.jpg");
        product1.setCategory(testCategory);
        product2.setCategory(testCategory);
        productRepository.save(product2);
        //Calling the method to test
        List<Product> products = productService.getProductsByCategoryId(testCategory.getId_category());
        Assertions.assertEquals(2, products.size());
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
