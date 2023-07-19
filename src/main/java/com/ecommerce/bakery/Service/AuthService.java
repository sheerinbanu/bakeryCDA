package com.ecommerce.bakery.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ecommerce.bakery.Model.User;
import com.ecommerce.bakery.Model.Role;
import com.ecommerce.bakery.Repository.UserRepository;
@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public void createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = Role.CLIENT;
        user.setRole(role);
        userRepository.save(user);
    }

    public void createNewAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = Role.ADMIN;
        user.setRole(role);
        userRepository.save(user);
    }
}
