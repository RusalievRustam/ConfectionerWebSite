package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.FinishedProduct;
import com.example.ConfectionerWebsite.services.FinishedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FinishedProductController {

    private final FinishedProductService finishedProductService;

    @GetMapping("/products")
    public String getFinishedProducts(Model model) {
        List<FinishedProduct> products = finishedProductService.getAllProducts();
        model.addAttribute("products", products);
        return "finished_products";
    }

    @GetMapping("get/{id}")
    public String finishedProductInfo(@PathVariable Long id, Model model) {
        FinishedProduct product = finishedProductService.getFinishedProductById(id);
        model.addAttribute("finishedProduct", product);
        return "finished_product_info";
    }

    @GetMapping("/create")
    public String createFinishedProductForm(Model model) {
        model.addAttribute("finishedProduct", new FinishedProduct());
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

    @PostMapping("/delete/{id}")
    public String deleteFinishedProduct(@PathVariable Long id) {
        finishedProductService.deleteFinishedProduct(id);
        return "redirect:/products";
    }
}