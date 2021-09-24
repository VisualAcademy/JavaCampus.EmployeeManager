package com.hawaso.javacampus.controllers.employees;

import com.hawaso.javacampus.services.employees.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService _service;

    public EmployeeController(EmployeeService service) {
        this._service = service;
    }

    @GetMapping(value = {"", "/index", "/list"})
    public String index(Model model) {
        var employees = _service.getAll();
        model.addAttribute("employees", employees);
        return "views/employees/index";
    }
    
    @GetMapping(value = "/create")
    public String create() {
        return "views/employees/create";
    }
}
