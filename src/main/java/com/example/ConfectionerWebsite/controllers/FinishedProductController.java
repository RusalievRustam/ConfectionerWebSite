package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.FinishedProduct;
import com.example.ConfectionerWebsite.entities.MeasurementUnit;
import com.example.ConfectionerWebsite.services.FinishedProductService;
import com.example.ConfectionerWebsite.services.MeasurementUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FinishedProductController {

    private final FinishedProductService finishedProductService;
    private final MeasurementUnitService measurementUnitService;

    @GetMapping("/products")
    public String getFinishedProducts(Model model) {
        List<FinishedProduct> products = finishedProductService.getAllProducts();
        model.addAttribute("products", products);
        return "finished_products";
    }

    @GetMapping("/create")
    public String createFinishedProductForm(Model model) {
        List<MeasurementUnit> measurementUnits = measurementUnitService.getAllMeasurementUnits();
        model.addAttribute("finishedProduct", new FinishedProduct());
        model.addAttribute("measurementUnits", measurementUnits);
        return "create_finished_product";
    }

    @PostMapping("/create")
    public String createFinishedProduct(@ModelAttribute FinishedProduct finishedProduct) {
        finishedProductService.createFinishedProduct(finishedProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editFinishedProductForm(@PathVariable Long id, Model model) {
        FinishedProduct product = finishedProductService.getFinishedProductById(id);
        model.addAttribute("finishedProduct", product);
        return "edit_finished_product";
    }

    @PostMapping("/edit/{id}")
    public String updateFinishedProduct(@PathVariable Long id, @ModelAttribute FinishedProduct finishedProduct) {
        finishedProduct.setId(id);
        finishedProductService.updateFinishedProduct(finishedProduct);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteFinishedProduct(@PathVariable Long id) {
        finishedProductService.deleteFinishedProduct(id);
        return "redirect:/products";
    }
}