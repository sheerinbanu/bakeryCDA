package com.ecommerce.bakery;


import ch.qos.logback.core.model.Model;
import com.ecommerce.bakery.Model.Cart;
import com.ecommerce.bakery.Model.Order;
import com.ecommerce.bakery.Model.Selection;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Service.CartService;
import com.ecommerce.bakery.Service.OrderService;
import com.ecommerce.bakery.Service.SelectionService;
import com.ecommerce.bakery.Service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        // Create mock objects
        Cart cart = new Cart();
        Order order = new Order();
        Selection selection = new Selection();
        User user = new User();
        org.springframework.security.core.Authentication authentication = mock(org.springframework.security.core.Authentication.class);
        Model model = mock(Model.class);
        BindingResult bindingResult = mock(BindingResult.class);

        // Set up the mock behavior
        when(authentication.getName()).thenReturn("username");
        when(userService.findByUsername("username")).thenReturn(user);
        when(user.getId_user()).thenReturn(1);
        when(selectionService.getAllSelection()).thenReturn(Arrays.asList(selection));

        // Call the method
        String result = cartService.submitCartForm(cart, order, selection, user, authentication, (org.springframework.ui.Model) model, bindingResult);

        // Assertions
        assertEquals("redirect:/orderHistory", result);

        // Verify that the necessary methods were called
        verify(cartService).insertCart(cart);
        verify(selection).setCart(cart);
        verify(selectionService).insertSelection(selection);
        verify(order).setCart(cart);
        verify(order).setUser(user);
        verify(orderService).insertOrder(order);
    }
}

