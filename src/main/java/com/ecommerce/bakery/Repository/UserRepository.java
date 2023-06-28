package com.ecommerce.bakery.Repository;

import com.ecommerce.bakery.Model.Role;
import com.ecommerce.bakery.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    List<User> findAllByRole(Role role);
    Optional<User> findById(int id);
    void deleteById(int id);
}
