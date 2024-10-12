package com.rayan.salarytracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())  // Disable CSRF protection
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/auth/login", "/auth/register").permitAll()  // Public endpoints
            .anyRequest().authenticated()  // Secure all other endpoints
        )
        .httpBasic(Customizer.withDefaults());  // Basic authentication

    return http.build();
    }
}
