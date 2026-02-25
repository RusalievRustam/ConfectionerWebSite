package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Budget;
import com.example.ConfectionerWebsite.entities.BusinessLoan;
import com.example.ConfectionerWebsite.entities.LoanPayment;
import com.example.ConfectionerWebsite.repositories.BudgetRepository;
import com.example.ConfectionerWebsite.repositories.BusinessLoanRepository;
import com.example.ConfectionerWebsite.repositories.LoanPaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final BusinessLoanRepository loanRepository;
    private final LoanPaymentRepository paymentRepository;
    private final BudgetRepository budgetRepository;

    public List<BusinessLoan> getAll() {
        return loanRepository.findAll();
    }

    @Transactional
    public BusinessLoan takeLoan(String bankName, Double principal, Double interestRate, Integer termMonths) {
        Budget budget = budgetRepository.findById(1L).orElseThrow();

        if (principal == null || principal <= 0) throw new RuntimeException("Сумма кредита должна быть > 0");
        if (interestRate == null || interestRate < 0) throw new RuntimeException("Процент не может быть отрицательным");
        if (termMonths == null || termMonths <= 0) throw new RuntimeException("Срок должен быть > 0");

        BusinessLoan loan = new BusinessLoan();
        loan.setBankName(bankName);
        loan.setPrincipal(principal);
        loan.setInterestRate(interestRate);
        loan.setTermMonths(termMonths);
        loan.setStartDate(java.time.LocalDate.now());
        loan.setOutstanding(principal);
        loan.setStatus("ACTIVE");

        // деньги пришли в бизнес
        budget.setTotalAmount(budget.getTotalAmount() + principal);
        budgetRepository.save(budget);

        return loanRepository.save(loan);
    }

    @Transactional
    public void payLoan(Long loanId, Double amount) {
        BusinessLoan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Кредит не найден"));

        if (!"ACTIVE".equals(loan.getStatus())) {
            throw new RuntimeException("Кредит уже закрыт");
        }

        Budget budget = budgetRepository.findById(1L).orElseThrow();

        if (amount == null || amount <= 0) throw new RuntimeException("Платёж должен быть > 0");
        if (budget.getTotalAmount() < amount) throw new RuntimeException("Недостаточно денег в бюджете для платежа");

        // списываем из бюджета
        budget.setTotalAmount(budget.getTotalAmount() - amount);
        budgetRepository.save(budget);

        // уменьшаем долг
        loan.setOutstanding(loan.getOutstanding() - amount);
        if (loan.getOutstanding() <= 0) {
            loan.setOutstanding(0.0);
            loan.setStatus("CLOSED");
        }
        loanRepository.save(loan);

        LoanPayment payment = new LoanPayment();
        payment.setLoan(loan);
        payment.setAmount(amount);
        payment.setPayDate(java.time.LocalDate.now());
        paymentRepository.save(payment);
    }
}