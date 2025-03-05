package com.example.ConfectionerWebsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/mainMenu") // Обрабатывает запрос на главную страницу
    public String home(Model model) {
        model.addAttribute("title", "Главное меню");
        return "index"; // Имя шаблона без .ftlh
    }
}