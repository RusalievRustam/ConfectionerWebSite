package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.FinishedProduct;
import com.example.ConfectionerWebsite.entities.MeasurementUnit;
import com.example.ConfectionerWebsite.services.MeasurementUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MeasurementUnitController {

    private final MeasurementUnitService measurementUnitService;


    @GetMapping("/measurementUnits")
    public String getAllMeasurementUnits(Model model) {
        List<MeasurementUnit> measurementUnits = measurementUnitService.getAllMeasurementUnits();
        model.addAttribute("measurementUnits", measurementUnits);
        return "measurement_units"; // Имя HTML-страницы
    }

    @GetMapping("/units/create")
    public String createMeasurementUnitForm(Model model) {
        model.addAttribute("measurementUnit", new MeasurementUnit());
        return "create_measurement_unit"; // Имя HTML-страницы для создания единицы измерения
    }

    @PostMapping("/units/create")
    public String createMeasurementUnit(@ModelAttribute MeasurementUnit measurementUnit) {
        measurementUnitService.createMeasurementUnit(measurementUnit);
        return "redirect:/measurement-units"; // Перенаправление на список единиц измерения
    }

    @GetMapping("/units/edit/{id}")
    public String editMeasurementUnitForm(@PathVariable Long id, Model model) {
        MeasurementUnit measurementUnit = measurementUnitService.getMeasurementUnitById(id);
        model.addAttribute("measurementUnit", measurementUnit);
        return "edit_measurement_unit"; // Имя HTML-страницы для редактирования единицы измерения
    }

    @PostMapping("/units/edit/{id}")
    public String updateMeasurementUnit(@PathVariable Long id, @ModelAttribute MeasurementUnit measurementUnit) {
        measurementUnit.setId(id); // Установите ID, чтобы обновить существующую запись
        measurementUnitService.createMeasurementUnit(measurementUnit);
        return "redirect:/measurement-units"; // Перенаправление на список единиц измерения
    }

    @GetMapping("/units/delete/{id}")
    public String deleteMeasurementUnit(@PathVariable Long id) {
        measurementUnitService.deleteMeasurementUnit(id);
        return "redirect:/measurement-units"; // Перенаправление на список единиц измерения
    }
}