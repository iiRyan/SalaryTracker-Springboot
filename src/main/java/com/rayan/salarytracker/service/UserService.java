package com.rayan.salarytracker.service;

import com.rayan.salarytracker.entity.User;
import com.rayan.salarytracker.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);
    User readUser(Long id);
    User updateUser(Long id,UserModel theUser);
    void deleteUSer(Long id);
    User getLoggedInUser();

}
