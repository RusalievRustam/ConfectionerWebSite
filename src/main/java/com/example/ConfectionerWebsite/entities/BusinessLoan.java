package com.example.ConfectionerWebsite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "business_loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankName;

    @Column(nullable = false)
    private Double principal;

    @Column(nullable = false)
    private Double interestRate;

    @Column(nullable = false)
    private java.time.LocalDate startDate;

    @Column(nullable = false)
    private Integer termMonths;

    @Column(nullable = false)
    private Double outstanding;

    @Column(nullable = false)
    private String status; // ACTIVE/CLOSED
}
