package com.rayan.salarytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rayan.salarytracker.entity.Expense;
import com.rayan.salarytracker.service.ExpenseService;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;


    @GetMapping
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).toList();
    }

    @PostMapping
    public Expense saveExpense(@RequestBody Expense theExpense){
        return expenseService.saveExpense(theExpense);
    }
}
