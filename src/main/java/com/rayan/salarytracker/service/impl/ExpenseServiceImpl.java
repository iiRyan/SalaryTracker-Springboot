package com.rayan.salarytracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rayan.salarytracker.entity.Expense;
import com.rayan.salarytracker.exception.ResourceNotFoundException;
import com.rayan.salarytracker.repository.ExpenseRepository;
import com.rayan.salarytracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense with Id: " + id + " Not found"));

    }

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        Expense existExpense = getExpenseById(id);
        existExpense.setDescription(expense.getDescription() != null ? expense.getDescription() : existExpense.getDescription());
        existExpense.setAmount(expense.getAmount() != 0 ? expense.getAmount() : existExpense.getAmount());
        existExpense.setBank(expense.getBank() != null ? expense.getBank() : existExpense.getBank());
        existExpense.setStatus(expense.getStatus() != null ? expense.getStatus() : existExpense.getStatus());

        return expenseRepository.save(existExpense);
    }
}
