package com.rayan.salarytracker.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rayan.salarytracker.entity.User;
import com.rayan.salarytracker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      
       User existUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for email: " + email));
       return new org.springframework.security.core.userdetails.User(existUser.getEmail(),existUser.getPassword(), new ArrayList<>());
    }
    
}
