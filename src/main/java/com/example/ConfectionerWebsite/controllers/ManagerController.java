//package com.example.ConfectionerWebsite.controllers;
//
//import com.example.ConfectionerWebsite.entities.FinishedProduct;
//import com.example.ConfectionerWebsite.entities.RawMaterial;
//import com.example.ConfectionerWebsite.services.ManagerService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/manager")
//public class ManagerController {
//
//    private final ManagerService managerService;
//
//    public ManagerController(ManagerService managerService) {
//        this.managerService = managerService;
//    }
//
//    @GetMapping
//    public String managerPage(Model model) {
//        model.addAttribute("title", "Менеджерская панель");
//        return "manager"; // имя шаблона без .ftlh
//    }
//
//    @PostMapping("/addFinishedProduct")
//    public ResponseEntity<FinishedProduct> addFinishedProduct(@RequestBody FinishedProduct product) {
//        FinishedProduct newProduct = managerService.addProduct(product);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
//    }
//
//    @PostMapping("/addRawMaterial")
//    public ResponseEntity<RawMaterial> addRawMaterial(@RequestBody RawMaterial rawMaterial) {
//        RawMaterial newRawMaterial = managerService.addRawMaterial(rawMaterial);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newRawMaterial);
//    }
//
//    @GetMapping("/products")
//    public ResponseEntity<List<FinishedProduct>> getAllProducts() {
//        List<FinishedProduct> products = managerService.getAllFinishedProducts();
//        return ResponseEntity.ok(products);
//    }
//
//    @GetMapping("/rawMaterials")
//    public ResponseEntity<List<RawMaterial>> getAllRawMaterials() {
//        List<RawMaterial> rawMaterials = managerService.getAllRawMaterials();
//        return ResponseEntity.ok(rawMaterials);
//    }
//
//    @DeleteMapping("/finishedProduct{id}")
//    public void deleteFinishedProduct(@PathVariable Long id) {
//        managerService.deleteFinishedProduct(id);
//    }
//}
