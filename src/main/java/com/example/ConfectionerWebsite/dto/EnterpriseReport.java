package com.example.ConfectionerWebsite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnterpriseReport {
    private Double sales;
    private Double purchases;
    private Double salaries;
    private Double loansTaken;
    private Double loanPayments;
    private Double profit; // sales - purchases - salaries
}