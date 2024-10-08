package com.rayan.salarytracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayan.salarytracker.entity.Salary;
import com.rayan.salarytracker.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {

    

    @Autowired
    SalaryRepository salaryRepo;


    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepo.findAll();

    }
    
}
