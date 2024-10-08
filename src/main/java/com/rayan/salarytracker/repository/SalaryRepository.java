package com.rayan.salarytracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rayan.salarytracker.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {
    
}
