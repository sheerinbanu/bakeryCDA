package com.ecommerce.bakery.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Model.Role;
import com.ecommerce.bakery.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public void createNewUser(User user) {
        // Encode the user's password using the BCryptPasswordEncoder
        // Remove any semicolons from the password before encoding
        String password = removeSemicolons(user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(password));
        Role role = Role.CLIENT;
        user.setRole(role);
        userRepository.save(user);
    }
    public void createNewAdmin(User user) {
        // Encode the admin's password using the BCryptPasswordEncoder
        // Remove any semicolons from the password before encoding
        String password = removeSemicolons(user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(password));
        Role role = Role.ADMIN;
        user.setRole(role);
        userRepository.save(user);
    }
    private String removeSemicolons(String password) {
        // Replace semicolons (;) with an empty string to remove them from the password
        return password.replace(";", "");
    }
}
