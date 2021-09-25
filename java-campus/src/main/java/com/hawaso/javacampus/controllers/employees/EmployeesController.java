package com.hawaso.javacampus.controllers.employees;

import java.util.List;
import java.util.Optional;

import com.hawaso.javacampus.models.employees.Employee;
import com.hawaso.javacampus.repositories.employees.EmployeeRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeesController {
    // Repository 인터페이스 주입
    private final EmployeeRepository _repository;

    public EmployeesController(EmployeeRepository repository) {
        this._repository = repository;
    }

    // 출력
    @GetMapping("/api/employees")
    public List<Employee> all() {
        return _repository.findAll();
    }

    // 입력
    @PostMapping("/api/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return _repository.save(newEmployee);
    }

    // 상세
    @GetMapping("/api/employees/{id}")
    public Optional<Employee> one(@PathVariable Integer id) {
        return _repository.findById(id);
    }

    // 수정
    @PutMapping("/api/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Integer id) {
        return _repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return _repository.save(employee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return _repository.save(newEmployee);
        });
    }

    // 삭제
    @DeleteMapping("/api/employees/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        _repository.deleteById(id);
    }
}
