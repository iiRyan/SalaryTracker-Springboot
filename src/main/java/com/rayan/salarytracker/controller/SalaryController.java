package com.rayan.salarytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rayan.salarytracker.entity.Salary;
import com.rayan.salarytracker.service.SalaryService;

@RestController
@RequestMapping(value = "/salaries")
public class SalaryController {
    
    @Autowired
    private SalaryService salaryService;

    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }
}
