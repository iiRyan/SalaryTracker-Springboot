package com.rayan.salarytracker.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rayan.salarytracker.entity.Salary;
import com.rayan.salarytracker.exception.ResourceNotFoundException;
import com.rayan.salarytracker.repository.SalaryRepository;
import com.rayan.salarytracker.service.SalaryService;
import com.rayan.salarytracker.service.UserService;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepo;
    @Autowired
    private UserService userService;

    @Override
    public Page<Salary> getAllSalaries(Pageable page) {
        return salaryRepo.findByUserId(userService.getLoggedInUser().getId(),page);

    }

    @Override
    public Salary getSalaryById(Long id) {
        Optional<Salary> salary = salaryRepo.findByUserIdAndId(userService.getLoggedInUser().getId(),id);
        if (salary.isPresent()) {
            return salary.get();
        }
        throw new ResourceNotFoundException("Salary not found for Id: " + id);
    }

    @Override
    public void deleteSalary(Long id) {
        Salary salary = getSalaryById(id);
        salaryRepo.delete(salary);
    }

    @Override
    public Salary insertSalary(Salary theSalary) {
        theSalary.setUser(userService.getLoggedInUser());
       return salaryRepo.save(theSalary);

        
    }

    @Override
    public Salary updateSalary(Long id,Salary theSalary) {
        Salary existingSalary = getSalaryById(id);
        // updating fields
        existingSalary.setMonth(theSalary.getMonth() != null ? theSalary.getMonth() : existingSalary.getMonth());
        existingSalary.setDescription(theSalary.getDescription() != null ? theSalary.getDescription() : existingSalary.getDescription());
        existingSalary.setAmount(theSalary.getAmount() != 0 ? theSalary.getAmount() : existingSalary.getAmount());
        existingSalary.setDate(theSalary.getDate() != null ? theSalary.getDate() : existingSalary.getDate());
        return salaryRepo.save(existingSalary);
    }

    @Override
    public List<Salary> readByMonth(String month, Pageable page) {
     return salaryRepo.findByMonth(month, page).toList();
        
    }

}
