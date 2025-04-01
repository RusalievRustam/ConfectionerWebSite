package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.MeasurementUnit;
import com.example.ConfectionerWebsite.entities.RawMaterial;
import com.example.ConfectionerWebsite.services.MeasurementUnitService;
import com.example.ConfectionerWebsite.services.RawMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;
    private final MeasurementUnitService measurementUnitService;


    // Получение списка всех сырьевых материалов
    @GetMapping("/rawMaterials")
    public String getAllRawMaterials(Model model) {
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterials();
        model.addAttribute("rawMaterials", rawMaterials);
        return "raw_materials"; // Имя HTML-страницы
    }

    // Форма для создания нового сырья
    @GetMapping("/rawMaterial/create")
    public String createRawMaterialForm(Model model) {
        List<MeasurementUnit> measurementUnits = measurementUnitService.getAllMeasurementUnits();
        model.addAttribute("rawMaterial", new RawMaterial());
        model.addAttribute("measurementUnits", measurementUnits);
        return "create_raw_material"; // Имя HTML-страницы для создания сырья
    }

    // Сохранение нового сырья
    @PostMapping("/rawMaterial/create")
    public String createRawMaterial(@ModelAttribute RawMaterial rawMaterial) {
        rawMaterialService.createRawMaterial(rawMaterial);
        return "redirect:/rawMaterials"; // Перенаправление на список сырья
    }

    // Получение формы для редактирования сырья
    @GetMapping("/rawMaterial/edit/{id}")
    public String editRawMaterialForm(@PathVariable Long id, Model model) {
        List<MeasurementUnit> measurementUnits = measurementUnitService.getAllMeasurementUnits();
        RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(id);
        model.addAttribute("rawMaterial", rawMaterial);
        model.addAttribute("measurementUnits", measurementUnits);
        return "edit_raw_material"; // Имя HTML-страницы для редактирования сырья
    }

    // Обновление сырья
    @PostMapping("/rawMaterial/edit/{id}")
    public String updateRawMaterial(@PathVariable Long id, @ModelAttribute RawMaterial rawMaterial) {
        rawMaterial.setId(id); // Установите ID, чтобы обновить существующую запись
        rawMaterialService.updateRawMaterial(rawMaterial);
        return "redirect:/rawMaterials"; // Перенаправление на список сырья
    }

    // Удаление сырья
    @GetMapping("/rawMaterial/delete/{id}")
    public String deleteRawMaterial(@PathVariable Long id) {
        rawMaterialService.deleteRawMaterial(id);
        return "redirect:/rawMaterials"; // Перенаправление на список сырья
    }
}