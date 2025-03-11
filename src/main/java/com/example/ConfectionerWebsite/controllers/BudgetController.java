package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Budget;
import com.example.ConfectionerWebsite.services.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("/budget")
    public ResponseEntity<Map<String, Double>> getTotalBudget() {
        double totalBudget = budgetService.getAllBudget().get(0).getTotalAmount(); // Your logic to get budget sum
        Map<String, Double> response = new HashMap<>();
        response.put("budget", totalBudget);
        return ResponseEntity.ok(response);
    }
}
