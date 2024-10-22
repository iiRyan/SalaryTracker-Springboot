package com.rayan.salarytracker.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rayan.salarytracker.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {

    // SELECT * FROM salaries WHERE month =?
    Page<Salary>findByMonth(String month,Pageable page);

    Page<Salary> findByUserId(Long userId, Pageable page);

    Optional<Salary> findByUserIdAndId(Long userId, Long expenseId);

    Optional<Salary> findByUserIdAndMonth(Long userId, String month);

}
