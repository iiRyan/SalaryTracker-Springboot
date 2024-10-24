package com.rayan.salarytracker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.rayan.salarytracker.entity.*;

public interface ExpenseService {

    Page<Expense> getAllExpenses(Long salaryId,Pageable page);

    Expense getExpenseById(Long id);
    Expense saveExpense(ExpenseDto expense);

    void deleteExpense(Long id);

    Expense updateExpense(Long id, Expense expense);
}
