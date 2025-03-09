package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Budget;
import com.example.ConfectionerWebsite.entities.RawMaterialPurchase;
import com.example.ConfectionerWebsite.exceptions.NotEnoughFundException;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.BudgetRepository;
import com.example.ConfectionerWebsite.repositories.MaterialPurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MaterialPurchaseService {

    private MaterialPurchaseRepository materialPurchaseRepository;
    private BudgetRepository budgetRepository;

    public RawMaterialPurchase createPurchase(RawMaterialPurchase rawMaterialPurchase) throws NotEnoughFundException {
        Budget budget = budgetRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));

        if (budget.getTotalAmount() < rawMaterialPurchase.getTotalCost()) {
            throw new NotEnoughFundException("Not enough funds in the budget!");
        }
        budget.setTotalAmount(budget.getTotalAmount() - rawMaterialPurchase.getTotalCost());
        budgetRepository.save(budget);
        rawMaterialPurchase.setDate(LocalDate.now());
        return materialPurchaseRepository.save(rawMaterialPurchase);
    }

    public List<RawMaterialPurchase> getAllPurchases() {
        return materialPurchaseRepository.findAll();
    }

    public RawMaterialPurchase getPurchaseById(Long id) {
        return materialPurchaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found by id " + id));
    }

    public void deletePurchase(Long id) {
        materialPurchaseRepository.deleteById(id);
    }
}
