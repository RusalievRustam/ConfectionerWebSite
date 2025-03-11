package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Budget;
import com.example.ConfectionerWebsite.services.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("/budget")
    public String getBudget(Model model) {
        Budget budget = budgetService.getAllBudget().get(0);
        model.addAttribute("budget", budget);
        return "header"; // Имя шаблона FreeMarker
    }
}
