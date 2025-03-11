package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Budget;
import com.example.ConfectionerWebsite.repositories.BudgetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BudgetService {

    private BudgetRepository budgetRepository;

    public Budget createBudget(Budget budget){
        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudget() {
        return budgetRepository.findAll();
    }
}
