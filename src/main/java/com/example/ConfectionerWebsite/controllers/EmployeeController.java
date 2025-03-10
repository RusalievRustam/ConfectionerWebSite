package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employee/create")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employees", new Employee());
        return "create_employee";
    }

    @PostMapping("/employee/create")
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:employees";
    }
}
