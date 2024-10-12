package com.rayan.salarytracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rayan.salarytracker.entity.Expense;
import com.rayan.salarytracker.repository.ExpenseRepository;
import com.rayan.salarytracker.service.ExpenseService;
@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense saveExpense(Expense expense) {
       return expenseRepository.save(expense);
    }

}
