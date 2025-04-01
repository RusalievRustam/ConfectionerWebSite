package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.FinishedProduct;
import com.example.ConfectionerWebsite.entities.ProductSale;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
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
        model.addAttribute("productSale", new ProductSale());
        model.addAttribute("finishedProduct", finishedProduct);
        model.addAttribute("cost", finishedProduct.getTotalCost() / finishedProduct.getQuantity());
        model.addAttribute("employees", employees);
        return "product_sale";
    }

    @PostMapping("/product/sale")
    public String createProductSale(@ModelAttribute ProductSale productSale) {
        productSaleService.createProductSale(productSale);
        return "redirect:/productSales";
    }
}
