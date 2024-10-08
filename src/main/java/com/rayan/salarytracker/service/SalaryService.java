package com.rayan.salarytracker.service;

import java.util.List;

import com.rayan.salarytracker.entity.Salary;

public interface SalaryService {
    
    List<Salary> getAllSalaries();
    Salary getSalaryById(Long id);
    void deleteSalary(Long id);
}
