package com.ecommerce.bakery;


import ch.qos.logback.core.model.Model;
import com.ecommerce.bakery.Model.*;
import com.ecommerce.bakery.Service.CartService;
import com.ecommerce.bakery.Service.OrderService;
import com.ecommerce.bakery.Service.SelectionService;
import com.ecommerce.bakery.Service.UserService;
import org.mockito.ArgumentCaptor;
import org.springframework.security.core.Authentication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CartServiceTests {
    @InjectMocks
    private CartService cartService;

    @Mock
    private UserService userService;

    @Mock
    private SelectionService selectionService;

    @Mock
    private OrderService orderService;
        @Test
        public void testSubmitCartForm() {
            // Mock Authentication and User
            Authentication authentication = mock(Authentication.class);
            User user = new User(); // Create a User instance with required data
            user.setId(7);
            user.setUsername("Julie");

            when(authentication.getName()).thenReturn("Julie");
            when(userService.findByUsername("Julie")).thenReturn(user);

            // Create a Cart, Selection, and Order instance with required data
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setTotal_price(400);

            Selection selection = new Selection();
            selection.setId_user(7);// You may need to create this with required data
            selection.setQuantity(10);
            selection.setTotal(200);
            Order order = new Order(); // You may need to create this with required data
            order.setId_order(4);
            order.setOrder_date("9/22/2023");
            order.setValidate(false);

            // Mock SelectionService methods
            ArgumentCaptor<Selection> selectionCaptor = ArgumentCaptor.forClass(Selection.class);
            doNothing().when(selectionService).insertSelection(selectionCaptor.capture());

            Selection capturedSelection = selectionCaptor.getValue();
            assertEquals(selection, capturedSelection);


            // Mock UserService method
            when(userService.findByUsername("Julie")).thenReturn(user);

            // Call the method
            String result = cartService.submitCartForm(cart, order, selection, user, authentication);

            // Verify that the methods were called as expected
            verify(userService, times(1)).findByUsername("Julie");
            verify(selectionService, times(1)).getAllSelection(); // You may need to mock this method in your SelectionService
            verify(selectionService, times(1)).insertSelection(selection); // You may need to mock this method in your SelectionService
            verify(orderService, times(1)).insertOrder(order); // You may need to mock this method in your OrderService

            // Add assertions for the result if needed
            assertEquals("redirect:/orderHistory", result);
        }
    }


