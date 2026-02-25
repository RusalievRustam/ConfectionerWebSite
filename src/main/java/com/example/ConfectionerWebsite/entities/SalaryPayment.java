package com.example.ConfectionerWebsite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "salary_payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "pay_date", nullable = false)
    private LocalDate payDate;

    @Column(name = "note")
    private String note;
}