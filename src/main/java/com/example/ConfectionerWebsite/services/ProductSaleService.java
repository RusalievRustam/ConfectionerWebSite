package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Budget;
import com.example.ConfectionerWebsite.entities.FinishedProduct;
import com.example.ConfectionerWebsite.entities.ProductSale;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.BudgetRepository;
import com.example.ConfectionerWebsite.repositories.FinishedProductRepository;
import com.example.ConfectionerWebsite.repositories.ProductSaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductSaleService {

    private ProductSaleRepository productSaleRepository;
    private FinishedProductRepository finishedProductRepository;
    private BudgetRepository budgetRepository;

    public ProductSale createProductSale(ProductSale productSale) throws ResourceNotFoundException {
        FinishedProduct finishedProduct = finishedProductRepository.findById(productSale.getFinishedProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Готовый продукт не найден"));
        if (finishedProduct.getQuantity() < productSale.getQuantity()) {
            throw new RuntimeException("Недостаточно количества продукта для продажи");
        }

        double CostPerProduct = finishedProduct.getTotalCost() / finishedProduct.getQuantity();

        Budget budget = budgetRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Бюджет не найден"));

        double markup = budget.getMarkup();
        double totalRevenue = CostPerProduct * productSale.getQuantity();
        double profit = totalRevenue * markup;

        finishedProduct.setQuantity(finishedProduct.getQuantity() - productSale.getQuantity());
        finishedProductRepository.save(finishedProduct);

        finishedProduct.setTotalCost(finishedProduct.getTotalCost() - productSale.getTotalCost());

        budget.setTotalAmount((budget.getTotalAmount() + profit));
        budgetRepository.save(budget);

        return productSaleRepository.save(productSale);
    }

    public List<ProductSale> getAllProductSales() {
        return productSaleRepository.findAll();
    }

    public ProductSale getProductSaleById(Long id) {
        return productSaleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product for sale not found by id " + id));
    }
}
