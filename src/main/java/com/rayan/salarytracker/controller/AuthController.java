package com.rayan.salarytracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rayan.salarytracker.entity.AuthModel;
import com.rayan.salarytracker.entity.User;
import com.rayan.salarytracker.entity.UserModel;
import com.rayan.salarytracker.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody AuthModel authModel) {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserModel theUser) {
        return new ResponseEntity<User>(userService.createUser(theUser), HttpStatus.CREATED);
    }
}
