package com.rayan.salarytracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rayan.salarytracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long>{
    Page<Expense> findBySalaryId(Long salaryId,Pageable page);
}