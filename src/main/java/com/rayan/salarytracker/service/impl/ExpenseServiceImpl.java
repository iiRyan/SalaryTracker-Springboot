package com.rayan.salarytracker.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rayan.salarytracker.entity.Expense;
import com.rayan.salarytracker.entity.ExpenseDto;
import com.rayan.salarytracker.entity.Salary;
import com.rayan.salarytracker.exception.ResourceNotFoundException;
import com.rayan.salarytracker.repository.ExpenseRepository;
import com.rayan.salarytracker.service.ExpenseService;
import com.rayan.salarytracker.service.SalaryService;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> getAllExpenses(Long salaryId, Pageable page) {
        System.out.println("Salary Id === " + salaryId);
        return expenseRepository.findBySalaryId(salaryId, page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense with Id: " + id + " Not found"));

    }

    public Expense saveExpense(ExpenseDto expenseDto) {
        // Find the related Salary using the salaryId from the DTO
        Salary salary = salaryService.getSalaryById(expenseDto.getSalaryId());

        // Map DTO fields to the Expense entity
        Expense expense = new Expense();
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(expenseDto.getCategory() != null ? expenseDto.getCategory() : "needs");
        expense.setBank(expenseDto.getBank());
        expense.setStatus(expenseDto.getStatus());
        expense.setSalary(salary); // Set the related Salary

        return expenseRepository.save(expense); // Save the Expense entity
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        Expense existExpense = getExpenseById(id);
        existExpense.setDescription(
                expense.getDescription() != null ? expense.getDescription() : existExpense.getDescription());
        existExpense.setAmount(expense.getAmount() != 0 ? expense.getAmount() : existExpense.getAmount());
        existExpense.setBank(expense.getBank() != null ? expense.getBank() : existExpense.getBank());
        existExpense.setStatus(expense.getStatus() != null ? expense.getStatus() : existExpense.getStatus());

        return expenseRepository.save(existExpense);
    }
}
