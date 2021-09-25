package com.hawaso.javacampus.controllers.employees;

import com.hawaso.javacampus.models.employees.Employee;
import com.hawaso.javacampus.services.employees.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String create(Model model) {

        model.addAttribute("employee", new Employee());

        return "views/employees/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee model) {
        _service.save(model);
        return "redirect:/employee/list";
    }
}
