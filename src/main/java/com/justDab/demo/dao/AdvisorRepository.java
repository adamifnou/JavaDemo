package com.justDab.demo.dao;

import com.justDab.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorRepository extends JpaRepository<Employee, Integer> {
    Employee getAdvisorById(int id);
}
