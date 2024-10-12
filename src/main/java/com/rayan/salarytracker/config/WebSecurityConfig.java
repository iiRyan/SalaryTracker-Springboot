package com.rayan.salarytracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/login", "/auth/register").permitAll() // Public endpoints
                        .anyRequest().authenticated() // Secure all other endpoints
                )
                .httpBasic(Customizer.withDefaults()); // Basic authentication

        return http.build();
    }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService() {
    //     UserDetails user = User.withUsername("Rayan")
    //             .password(passwordEncoder().encode("123456"))
    //             .roles("USER")
    //             .build();
    //     return new InMemoryUserDetailsManager(user);
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("rayan")
                .password("{noop}test123")
                .roles("admin")
                .build();
        UserDetails mary = User.builder()
                .username("jojo")
                .password("{noop}test123")
                .roles("user")
                .build();
       
        return new InMemoryUserDetailsManager(john, mary);
    }

}
