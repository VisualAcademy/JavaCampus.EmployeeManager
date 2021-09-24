package com.hawaso.javacampus.repositories;

import java.util.List;

import com.hawaso.javacampus.models.employees.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public List<Employee> findByOrderByIdDesc(); 
    public List<Employee> findByOrderByFirstNameAsc(); 
    public List<Employee> findAllByOrderByLastNameAsc(); 
    public List<Employee> findByFirstNameContainsOrLastNameContainingIgnoreCase(String firstName, String lastName); 
}
