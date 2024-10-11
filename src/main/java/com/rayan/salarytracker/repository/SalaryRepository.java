package com.rayan.salarytracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rayan.salarytracker.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long> {

    // SELECT * FROM salaries WHERE month =?
    Page<Salary>findByMonth(String month,Pageable page);
}
