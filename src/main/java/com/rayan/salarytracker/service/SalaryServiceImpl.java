package com.rayan.salarytracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rayan.salarytracker.entity.Salary;
import com.rayan.salarytracker.exception.ResourceNotFoundException;
import com.rayan.salarytracker.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepo;

    @Override
    public Page<Salary> getAllSalaries(Pageable page) {
        return salaryRepo.findAll(page);

    }

    @Override
    public Salary getSalaryById(Long id) {
        Optional<Salary> salary = salaryRepo.findById(id);
        if (salary.isPresent()) {
            return salary.get();
        }
        throw new ResourceNotFoundException("Salary not found for Id: " + id);
    }

    @Override
    public void deleteSalary(Long id) {
        salaryRepo.deleteById(id);
    }

    @Override
    public Salary insertSalary(Salary theSalary) {
       return salaryRepo.save(theSalary);

        
    }

    @Override
    public Salary updateSalary(Long id,Salary theSalary) {
        Salary existingSalary = getSalaryById(id);
        // updating fields
        existingSalary.setMonth(theSalary.getMonth());
        existingSalary.setDescription(theSalary.getDescription());
        existingSalary.setAmount(theSalary.getAmount());
        existingSalary.setDate(theSalary.getDate());
        return salaryRepo.save(existingSalary);
    }

}
