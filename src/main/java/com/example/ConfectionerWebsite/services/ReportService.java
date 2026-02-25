package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.dto.EnterpriseReport;
import com.example.ConfectionerWebsite.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ProductSaleRepository productSaleRepository;
    private final RawMaterialRepository rawMaterialRepository;

    // если ЛР4 и ЛР5 добавлены — оставляй, если нет — временно убери
    private final SalaryPaymentRepository salaryPaymentRepository;
    private final BusinessLoanRepository businessLoanRepository;
    private final LoanPaymentRepository loanPaymentRepository;

    public EnterpriseReport build(LocalDate from, LocalDate to) {

        double sales = productSaleRepository.sumSales(from, to);
        double purchases = rawMaterialRepository.sumPurchases(from, to);
        double salaries = salaryPaymentRepository.sumSalaries(from, to);
        double loansTaken = businessLoanRepository.sumLoansTaken(from, to);
        double loanPayments = loanPaymentRepository.sumLoanPayments(from, to);

        double profit = sales - purchases - salaries;

        return new EnterpriseReport(sales, purchases, salaries, loansTaken, loanPayments, profit);
    }
}