package com.rayan.salarytracker.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayan.salarytracker.entity.User;
import com.rayan.salarytracker.entity.UserModel;
import com.rayan.salarytracker.exception.ItemAlreadyExistException;
import com.rayan.salarytracker.exception.ResourceNotFoundException;
import com.rayan.salarytracker.repository.UserRepository;
import com.rayan.salarytracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ItemAlreadyExistException("User with email " + user.getEmail() + " already exist!");
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    // As of now i guess we don't need to change 'email' & 'password'.
    @Override
    public User updateUser(Long id, UserModel theUser) {
       User existUser = readUser(id);
       existUser.setName(theUser.getName());
       return userRepository.save(existUser);
    }

    @Override
    public void deleteUSer(Long id) {
      User existUser = readUser(id);
      userRepository.delete(existUser);
    }

}