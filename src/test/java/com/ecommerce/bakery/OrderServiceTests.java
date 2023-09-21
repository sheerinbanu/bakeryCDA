package com.ecommerce.bakery;
import com.ecommerce.bakery.Model.Order;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Service.OrderService;
import com.ecommerce.bakery.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
public class OrderServiceTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        // Initialize the mocks and setup behavior here
    }

    @Test
    void testGetAllOrdersByUser() {
        // Mock dependencies
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        Model model = mock(Model.class);
        User user = new User(/* Set user properties */);
        Order order1 = new Order(/* Set order properties */);
        Order order2 = new Order(/* Set order properties */);

        // Mock behavior of userService
        when(userService.findByUsername("username")).thenReturn(user);

        // Create a list of orders
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        // Mock behavior of orderService
        when(orderService.getAllOrder()).thenReturn(orderList);

        // Call the method to test
        String result = orderService.getAllOrdersByUser(authentication, model);

        // Perform assertions
        // Add assertions based on the expected behavior of the method
        // For example, check if the result is as expected and if the model attributes are set correctly

        // Verify interactions
        verify(userService).findByUsername("username");
        verify(orderService).getAllOrder();
        verify(model).addAttribute("orders", orderList);
    }
}
