package com.rayan.salarytracker.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rayan.salarytracker.entity.Salary;
import com.rayan.salarytracker.exception.ItemAlreadyExistException;
import com.rayan.salarytracker.exception.ResourceNotFoundException;
import com.rayan.salarytracker.repository.SalaryRepository;
import com.rayan.salarytracker.service.SalaryService;
import com.rayan.salarytracker.service.UserService;

@Service
public class SalaryServiceImpl implements SalaryService {
    private static final Logger logger = LoggerFactory.getLogger(SalaryService.class);

    @Autowired
    private SalaryRepository salaryRepo;
    @Autowired
    private UserService userService;

    @Override
    public Page<Salary> getAllSalaries(Pageable page) {
        Long userId = userService.getLoggedInUser().getId();
        return salaryRepo.findByUserId(userId, page);
    }

    @Override
    public Salary getSalaryById(Long id) {
        Optional<Salary> salary = salaryRepo.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
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
        if (monthExist(theSalary.getMonth(),userService.getLoggedInUser().getId())) {
            throw new ItemAlreadyExistException("Month " + theSalary.getMonth() + " already exists.");
        }
        theSalary.setUser(userService.getLoggedInUser());
        return salaryRepo.save(theSalary);

    }

    @Override
    public Salary updateSalary(Long id, Salary theSalary) {
        Salary existingSalary = getSalaryById(id);
        // updating fields
        existingSalary.setMonth(theSalary.getMonth() != null ? theSalary.getMonth() : existingSalary.getMonth());
        existingSalary.setDescription(
                theSalary.getDescription() != null ? theSalary.getDescription() : existingSalary.getDescription());
        existingSalary.setAmount(theSalary.getAmount() != 0 ? theSalary.getAmount() : existingSalary.getAmount());
        existingSalary.setDate(theSalary.getDate() != null ? theSalary.getDate() : existingSalary.getDate());
        return salaryRepo.save(existingSalary);
    }

    @Override
    public List<Salary> readByMonth(String month, Pageable page) {
        return salaryRepo.findByMonth(month, page).toList();

    }

    private boolean monthExist(String month, Long userId) {
        logger.info("Checking month... " + month);
        Optional<Salary> salary = salaryRepo.findByUserIdAndMonth(userId,month);
        if (salary.isPresent()) {
            logger.info("Month already exist " + month);
            return true;
        }
        return false;

    }
}
