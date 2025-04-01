package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.ProductSale;
import com.example.ConfectionerWebsite.exceptions.NotEnoughResourceException;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.services.BudgetService;
import com.example.ConfectionerWebsite.services.EmployeeService;
import com.example.ConfectionerWebsite.services.FinishedProductService;
import com.example.ConfectionerWebsite.services.ProductSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ProductSaleController {

    private final ProductSaleService productSaleService;
    private final EmployeeService employeeService;
    private final FinishedProductService finishedProductService;
    private final BudgetService budgetService;

    @GetMapping("/productSales")
    public String getAllProductSales(Model model) {
        List<ProductSale> productSales = productSaleService.getAllProductSales();
        model.addAttribute("productSales", productSales);
        return "product_sales";
    }

    @GetMapping("/product/sale/{id}")
    public String createProductSaleForm(@PathVariable Long id, Model model) throws ResourceNotFoundException {
        final var finishedProduct = finishedProductService.getFinishedProductById(id);
        List<Employee> employees = employeeService.getAllEmployees();
        final var markup = budgetService.getBudgetById(1L).getMarkup();
        final var pricePerUnit = finishedProduct.getTotalCost() / finishedProduct.getQuantity();
        final var cost = pricePerUnit + pricePerUnit * markup / 100;
        model.addAttribute("productSale", new ProductSale());
        model.addAttribute("finishedProduct", finishedProduct);
        model.addAttribute("cost", cost);
        model.addAttribute("employees", employees);
        return "product_sale";
    }

    @PostMapping("/product/sale")
    public String createProductSale(@RequestBody ProductSale productSale) throws NotEnoughResourceException {
        productSaleService.createProductSale(productSale);
        return "redirect:/products";
    }
}
