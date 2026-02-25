package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/loans")
    public String list(Model model) {
        model.addAttribute("loans", loanService.getAll());
        return "loans";
    }

    @GetMapping("/loan/create")
    public String createForm() {
        return "create_loan";
    }

    @PostMapping("/loan/create")
    public String create(@RequestParam String bankName,
                         @RequestParam Double principal,
                         @RequestParam Double interestRate,
                         @RequestParam Integer termMonths,
                         Model model) {
        try {
            loanService.takeLoan(bankName, principal, interestRate, termMonths);
            return "redirect:/loans";
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "create_loan";
        }
    }

    @PostMapping("/loan/pay")
    public String pay(@RequestParam Long loanId,
                      @RequestParam Double amount,
                      Model model) {
        try {
            loanService.payLoan(loanId, amount);
            return "redirect:/loans";
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("loans", loanService.getAll());
            return "loans";
        }
    }
}