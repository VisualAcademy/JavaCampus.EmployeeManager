package com.hawaso.javacampus.controllers;

import java.util.List;
import java.util.Optional;

import com.hawaso.javacampus.models.Employee;
import com.hawaso.javacampus.repositories.EmployeeRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeesController {

  private final EmployeeRepository repository;

  public EmployeesController(EmployeeRepository repository) {
    this.repository = repository;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/employees")
  public List<Employee> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/employees")
  public Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item
  
  @GetMapping("/employees/{id}")
  public Optional<Employee> one(@PathVariable Integer id) {    
    return repository.findById(id);
  }

  @PutMapping("/employees/{id}")
  public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Integer id) {
    
    return repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @DeleteMapping("/employees/{id}")
  public  void deleteEmployee(@PathVariable Integer id) {
    repository.deleteById(id);
  }
}