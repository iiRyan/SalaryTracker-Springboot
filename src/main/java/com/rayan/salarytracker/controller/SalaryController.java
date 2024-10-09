package com.rayan.salarytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rayan.salarytracker.entity.Salary;
import com.rayan.salarytracker.service.SalaryService;

@RestController
@RequestMapping(value = "/salaries")
public class SalaryController {
    
    @Autowired
    private SalaryService salaryService;

    @GetMapping
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    @GetMapping("/{id}")
    public Salary getSalaryById(@PathVariable Long id){
        return salaryService.getSalaryById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSalaryById(@PathVariable("id") Long id){
        salaryService.deleteSalary(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Salary insertSalary(@RequestBody Salary theSalary){
        System.out.println("Inserting the salary... " + theSalary);
        return salaryService.insertSalary(theSalary);
    }
    
    @PutMapping("/{id}")
    public Salary updateSalary(@RequestBody Salary theSalary, @PathVariable Long id){
        return salaryService.updateSalary(id, theSalary);
    }
}
