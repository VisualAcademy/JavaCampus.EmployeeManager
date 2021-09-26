package com.hawaso.javacampus.controllers.employees;

import javax.validation.Valid;

import com.hawaso.javacampus.models.employees.Employee;
import com.hawaso.javacampus.services.employees.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

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
    public String save(@Valid @ModelAttribute("employee") Employee model, BindingResult br) {
        if (br.hasErrors()) {
            return "views/employees/create";
        }
        else {
            _service.save(model);
            return "redirect:/employee/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        _service.delete(id);
        return "redirect:/employee";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        var employee = _service.getById(id);
        model.addAttribute("employee", employee);
        return "views/employees/create";
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchQuery") String searchQuery, Model model) {
        if (searchQuery.trim().isEmpty() && StringUtils.isEmpty(searchQuery)) {
            return "redirect:/employee";
        }
        else {
            var employees = _service.searchAll(searchQuery, searchQuery);
            model.addAttribute("employees", employees);
            return "views/employees/index";
        }
    }
}
