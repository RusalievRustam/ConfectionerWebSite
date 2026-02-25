package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SalaryPaymentRepository extends JpaRepository<SalaryPayment, Long> {
    @Query("select coalesce(sum(sp.amount),0) from SalaryPayment sp where sp.payDate between :from and :to")
    Double sumSalaries(@Param("from") LocalDate from, @Param("to") LocalDate to);
}