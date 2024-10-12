package com.rayan.salarytracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayan.salarytracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    
} 