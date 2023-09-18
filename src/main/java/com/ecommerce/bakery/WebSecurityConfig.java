package com.ecommerce.bakery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;

    //In the context of BCryptPasswordEncoder, the SecureRandom instance
    // is responsible for generating the random salt used during the password hashing process.
    // This salt is then combined with the user's password before being passed to the bcrypt algorithm for hashing.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // BCryptPasswordEncoder with the provided version, strength, and SecureRandom instance
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, 12, new SecureRandom());
    }
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests.requestMatchers("/", "/registration", "/css/**", "/js/**", "/images/**").permitAll().requestMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()).formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/allCategories", true).failureUrl("/login-error").permitAll()).logout(logout -> logout.permitAll());
        return http.build();
    }

    @Autowired
    void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }
}
