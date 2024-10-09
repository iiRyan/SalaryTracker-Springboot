package com.rayan.salarytracker.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rayan.salarytracker.entity.Salary;

public interface SalaryService {
    
    Page<Salary> getAllSalaries(Pageable page);
    Salary getSalaryById(Long id);
    void deleteSalary(Long id);
    Salary insertSalary(Salary theSalary);
    Salary updateSalary(Long id, Salary theSalary);
}
