package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.services.EmployeeService;
import com.example.ConfectionerWebsite.services.SalaryPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class SalaryPaymentController {

    private final SalaryPaymentService salaryPaymentService;
    private final EmployeeService employeeService;

    @GetMapping("/salaryPayments")
    public String payments(Model model) {
        model.addAttribute("payments", salaryPaymentService.getAllPayments());
        return "salary_payments";
    }

    @GetMapping("/salaryPayment/create")
    public String form(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "create_salary_payment";
    }

    @PostMapping("/salaryPayment/create")
    public String create(Model model) {
        try {
            salaryPaymentService.paySalaryToAll();
            return "redirect:/salaryPayments";
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "create_salary_payment";
        }
    }
}