package com.ecommerce.bakery;
import com.ecommerce.bakery.Model.Order;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Repository.OrderRepository;
import com.ecommerce.bakery.Service.OrderService;
import com.ecommerce.bakery.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;



@SpringBootTest
public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private UserService userService;

    @Mock
    private Model model;



}

