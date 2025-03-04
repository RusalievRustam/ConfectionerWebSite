package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.RawMaterial;
import com.example.ConfectionerWebsite.services.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    // Получение списка всех сырьевых материалов
    @GetMapping
    public String getAllRawMaterials(Model model) {
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterials();
        model.addAttribute("rawMaterials", rawMaterials);
        return "raw_materials"; // Имя HTML-страницы
    }

    // Форма для создания нового сырья
    @GetMapping("/create")
    public String createRawMaterialForm(Model model) {
        model.addAttribute("rawMaterial", new RawMaterial());
        return "create_raw_material"; // Имя HTML-страницы для создания сырья
    }

    // Сохранение нового сырья
    @PostMapping("/create")
    public String createRawMaterial(@ModelAttribute RawMaterial rawMaterial) {
        rawMaterialService.createRawMaterial(rawMaterial);
        return "redirect:/raw-materials"; // Перенаправление на список сырья
    }

    // Получение формы для редактирования сырья
    @GetMapping("/edit/{id}")
    public String editRawMaterialForm(@PathVariable Long id, Model model) {
        RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(id);
        model.addAttribute("rawMaterial", rawMaterial);
        return "edit_raw_material"; // Имя HTML-страницы для редактирования сырья
    }

    // Обновление сырья
    @PostMapping("/edit/{id}")
    public String updateRawMaterial(@PathVariable Long id, @ModelAttribute RawMaterial rawMaterial) {
        rawMaterial.setId(id); // Установите ID, чтобы обновить существующую запись
        rawMaterialService.createRawMaterial(rawMaterial);
        return "redirect:/raw-materials"; // Перенаправление на список сырья
    }

    // Удаление сырья
    @GetMapping("/delete/{id}")
    public String deleteRawMaterial(@PathVariable Long id) {
        rawMaterialService.deleteRawMaterial(id);
        return "redirect:/raw-materials"; // Перенаправление на список сырья
    }
}