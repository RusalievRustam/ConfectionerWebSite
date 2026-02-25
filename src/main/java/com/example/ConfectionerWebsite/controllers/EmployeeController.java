package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.Position;
import com.example.ConfectionerWebsite.services.EmployeeService;
import com.example.ConfectionerWebsite.services.PositionService;
import com.example.ConfectionerWebsite.services.SalaryPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final SalaryPaymentService salaryPaymentService;
    private final PositionService positionService;

    @GetMapping("/employees")
    public String employees(Model model,
                            @ModelAttribute("errorMessage") String errorMessage,
                            @ModelAttribute("successMessage") String successMessage) {

        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("positions", positionService.getAll());
        return "employees";
    }

    @PostMapping("/employees/update")
    public String updateEmployee(@RequestParam Long id,
                                 @RequestParam Long positionId,
                                 @RequestParam Double salary) {
        employeeService.updateRoleAndSalary(id, positionId, salary);
        return "redirect:/employees";
    }


    @PostMapping("/salary/pay-all")
    public String payAll(RedirectAttributes ra) {
        try {
            salaryPaymentService.paySalaryToAll();
            ra.addFlashAttribute("successMessage", "Зарплата успешно выдана!");
            return "redirect:/salaryPayments";
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/employees";
        }
    }

    @PostMapping("/employees/create")
    public String createEmployee(@RequestParam String fullName,
                                 @RequestParam Long positionId,
                                 @RequestParam(required = false) Double salary,
                                 @RequestParam(required = false) String address,
                                 @RequestParam String phone,
                                 RedirectAttributes redirectAttributes) {
        try {
            employeeService.createEmployee(fullName, positionId, salary, address, phone);
            redirectAttributes.addFlashAttribute("successMessage", "Сотрудник добавлен");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/employees";
    }
}
