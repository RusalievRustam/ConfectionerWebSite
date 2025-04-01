package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.RawMaterial;
import com.example.ConfectionerWebsite.entities.RawMaterialPurchase;
import com.example.ConfectionerWebsite.exceptions.NotEnoughResourceException;
import com.example.ConfectionerWebsite.services.EmployeeService;
import com.example.ConfectionerWebsite.services.MaterialPurchaseService;
import com.example.ConfectionerWebsite.services.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class RawMaterialPurchaseController {

    private final MaterialPurchaseService materialPurchaseService;
    private final EmployeeService employeeService;
    private final RawMaterialService rawMaterialService;

    @GetMapping("/purchaseMaterials")
    public String getAllPurchaseMaterials(Model model) {
        List<RawMaterialPurchase> purchases = materialPurchaseService.getAllPurchases();
        model.addAttribute("purchaseMaterials", purchases);
        return ("purchase_materials");
    }

    @GetMapping("/purchaseMaterial/create")
    public String createPurchaseMaterialForm(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterials();
        model.addAttribute("purchaseMaterial", new RawMaterialPurchase());
        model.addAttribute("rawMaterials", rawMaterials);
        model.addAttribute("employees", employees);
        return "create_purchase_material";
    }

    @PostMapping("/purchaseMaterial/create")
    public String createPurchaseMaterial(@RequestBody RawMaterialPurchase rawMaterialPurchase) throws NotEnoughResourceException {
        materialPurchaseService.createPurchase(rawMaterialPurchase);
        return "redirect:/purchaseMaterials";
    }
}
