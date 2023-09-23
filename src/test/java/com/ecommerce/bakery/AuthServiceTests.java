package com.ecommerce.bakery;

import com.ecommerce.bakery.Model.Role;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Repository.UserRepository;
import com.ecommerce.bakery.Service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authService = new AuthService(userRepository, bCryptPasswordEncoder);
    }

    @Test
    public void testCreateNewUser() {
        // Create a user with some data
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        // Mocking the behavior of removeSemicolons (assuming it's a separate method)
        when(bCryptPasswordEncoder.encode("testpassword")).thenReturn("encodedpassword");

        // Call the method under test
        authService.createNewUser(user);

        // Verify that the password is encoded
        verify(bCryptPasswordEncoder).encode("testpassword");

        // Verify that the role is set to CLIENT
        Assertions.assertEquals(Role.CLIENT, user.getRole());

        // Verify that userRepository.save is called with the correct user
        verify(userRepository, times(1)).save(user);
    }
}
