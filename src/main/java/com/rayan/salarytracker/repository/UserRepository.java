package com.rayan.salarytracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rayan.salarytracker.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{
    boolean existsByEmail(String theEmail);
}
