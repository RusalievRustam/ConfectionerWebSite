package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Budget;
import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.SalaryPayment;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.BudgetRepository;
import com.example.ConfectionerWebsite.repositories.EmployeeRepository;
import com.example.ConfectionerWebsite.repositories.SalaryPaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryPaymentService {

    private final SalaryPaymentRepository salaryPaymentRepository;
    private final EmployeeRepository employeeRepository;
    private final BudgetRepository budgetRepository;

    @Transactional
    public void paySalaryToAll() {
        Budget budget = budgetRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Бюджет не найден"));

        if (budget.getTotalAmount() == null) budget.setTotalAmount(0.0);

        List<Employee> employees = employeeRepository.findAll();

        double total = employees.stream()
                .filter(e -> e.getSalary() != null && e.getSalary() > 0)
                .mapToDouble(Employee::getSalary)
                .sum();

        if (total <= 0) {
            throw new RuntimeException("Ни у одного сотрудника не задана зарплата");
        }

        if (budget.getTotalAmount() < total) {
            throw new RuntimeException("Недостаточно бюджета. Нужно: " + total + " сом");
        }

        // списываем бюджет
        budget.setTotalAmount(budget.getTotalAmount() - total);
        budgetRepository.save(budget);

        // создаём записи выплат
        LocalDate today = LocalDate.now();

        for (Employee e : employees) {
            if (e.getSalary() != null && e.getSalary() > 0) {
                SalaryPayment p = new SalaryPayment();
                p.setEmployee(e);
                p.setAmount(e.getSalary());
                p.setPayDate(today);
                p.setNote("Массовая выплата зарплаты");
                salaryPaymentRepository.save(p);
            }
        }
    }

    public List<SalaryPayment> getAllPayments() {
        return salaryPaymentRepository.findAll();
    }
}