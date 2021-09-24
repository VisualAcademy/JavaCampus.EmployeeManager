package com.hawaso.javacampus.services.employees;

import java.util.List;

import com.hawaso.javacampus.models.employees.Employee;
import com.hawaso.javacampus.repositories.employees.EmployeeRepository;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository _repository;

    public EmployeeService(EmployeeRepository repository) {
        this._repository = repository;
    }

    @Override
    public void save(Employee model) {
        _repository.save(model);
    }

    @Override
    public List<Employee> getAll() {
        return _repository.findByOrderByIdDesc(); 
    }

    @Override
    public Employee getById(int id) {
        var result = _repository.findById(id);

        if (result.isPresent()) {
            return result.get(); 
        }
        else {
            throw new RuntimeException("No Data."); // EmployeeNotFoundException...
        }
    }

    @Override
    public void delete(int id) {
        _repository.deleteById(id);
    }

    @Override
    public List<Employee> searchAll(String firstName, String lastName) {
        return _repository.findByFirstNameContainsOrLastNameContainingIgnoreCase(firstName, lastName);
    }
}
