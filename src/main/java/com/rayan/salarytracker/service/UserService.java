package com.rayan.salarytracker.service;

import com.rayan.salarytracker.entity.User;
import com.rayan.salarytracker.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);
    User readUser();
    User updateUser(UserModel theUser);
    void deleteUSer();
    User getLoggedInUser();

}
