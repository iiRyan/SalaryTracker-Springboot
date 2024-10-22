package com.rayan.salarytracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.rayan.salarytracker.entity.ExpenseDto;
import com.rayan.salarytracker.service.ExpenseService;
import com.rayan.salarytracker.service.SalaryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/expenses")
@CrossOrigin
public class ExpenseController {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

   
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/{id}")
    public List<Expense> getAllExpenses(@PathVariable Long id, Pageable page) {
        return expenseService.getAllExpenses(id, page).toList();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Expense saveExpense(@Valid @RequestBody ExpenseDto theExpense) {
        logger.info("Expense object... " + theExpense.toString());
        Expense expense = expenseService.saveExpense(theExpense);
        return expense;
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable Long id) {
        return expenseService.updateExpense(id, expense);
    }

}
