package com.hawaso.javacampus.services;

import java.util.List;

import com.hawaso.javacampus.models.Employee;

// IEmployeeService 인터페이스 설계 
public interface IEmployeeService {
    // 입력과 수정
    public void save(Employee model); // add(), update()
    // 출력
    public List<Employee> getAll(); // findAll()
    // 상세
    public Employee getById(int id); // findById()
    // 삭제
    public void delete(int id); // deleteById()
    // 검색
    public List<Employee> searchAll(String firstName, String lastName); // getByXXX()
}
