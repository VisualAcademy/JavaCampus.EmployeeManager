package com.hawaso.javacampus.repositories;

import com.hawaso.javacampus.models.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
