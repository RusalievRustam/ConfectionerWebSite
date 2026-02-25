package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.BusinessLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BusinessLoanRepository extends JpaRepository<BusinessLoan, Long> {
    @Query("select coalesce(sum(bl.principal),0) from BusinessLoan bl where bl.startDate between :from and :to")
    Double sumLoansTaken(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
