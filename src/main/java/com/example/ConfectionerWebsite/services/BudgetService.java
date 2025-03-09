package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Budget;
import com.example.ConfectionerWebsite.repositories.BudgetRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    private BudgetRepository budgetRepository;

    public Budget createBudget(Budget budget){
        return budgetRepository.save(budget);
    }
}
