package com.rayan.salarytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rayan.salarytracker.entity.Expense;
import com.rayan.salarytracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;


    @GetMapping
    public List<Expense> getAllExpenses(Pageable page){
        return expenseService.getAllExpenses(page).toList();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Expense saveExpense(@Valid @RequestBody Expense theExpense){
        return expenseService.saveExpense(theExpense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable Long id){
        return expenseService.updateExpense(id, expense);
    }

}
