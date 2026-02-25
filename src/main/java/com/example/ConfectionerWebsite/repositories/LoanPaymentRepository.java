package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.LoanPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Long> {
    @Query("select coalesce(sum(lp.amount),0) from LoanPayment lp where lp.payDate between :from and :to")
    Double sumLoanPayments(@Param("from") LocalDate from, @Param("to") LocalDate to);
}