package com.rayan.salarytracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/salaries")
public class SalaryController {
    

    @GetMapping("/")
    public String getAllSalaries(){
        return "List of salaries!";
    }
}
