package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.FinishedProduct;
import com.example.ConfectionerWebsite.entities.ProductProduction;
import com.example.ConfectionerWebsite.services.EmployeeService;
import com.example.ConfectionerWebsite.services.FinishedProductService;
import com.example.ConfectionerWebsite.services.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class ProductionController {

    private final ProductionService productionService;
    private final EmployeeService employeeService;
    private final FinishedProductService finishedProductService;


    @GetMapping("/productions")
    public String getAllProductions(Model model) {
        List<ProductProduction> productions = productionService.getAllProductions();
        List<FinishedProduct> finishedProducts = finishedProductService.getAllProducts();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("productions", productions);
        return "product_productions";
    }

    @GetMapping("/production/create")
    public String createProductionForm(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<FinishedProduct> finishedProducts = finishedProductService.getAllProducts();
        model.addAttribute("employees", employees);
        model.addAttribute("finishedProducts", finishedProducts);
        model.addAttribute("production", new ProductProduction());
        return "create_production";
    }

    @PostMapping("/production/create")
    public String createProduction(@ModelAttribute ProductProduction productProduction, Model model) {
        try {
            productionService.callProduceProductProcedure(
                    productProduction.getFinishedProduct().getId(),
                    productProduction.getQuantity(),
                    productProduction.getEmployee().getId()
            );
            return "redirect:/productions";
        } catch (Exception e) {
            // Проверяем, если ошибка связана с нехваткой сырья
            if (e.getMessage() != null && e.getMessage().contains("Недостаточно сырья")) {
                model.addAttribute("errorMessage", "Недостаточно сырья для выбранного продукта.");
            } else {
                model.addAttribute("errorMessage", "Произошла ошибка при создании производства.");
            }

            // Повторно подгружаем списки, чтобы форма отобразилась
            model.addAttribute("employees", employeeService.getAllEmployees());
            model.addAttribute("finishedProducts", finishedProductService.getAllProducts());
            model.addAttribute("production", productProduction);

            return "create_production";
        }
    }
}
