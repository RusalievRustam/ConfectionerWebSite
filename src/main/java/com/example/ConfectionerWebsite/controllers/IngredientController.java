package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Ingredient;
import com.example.ConfectionerWebsite.exceptions.NotEnoughMaterialsException;
import com.example.ConfectionerWebsite.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/ingredients")
    public String getAllIngredients(Model model) {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        model.addAttribute("ingredients", ingredients);
        return "ingredients"; // Имя HTML-страницы
    }

    @GetMapping("/ingredients/{id}")
    public String finishedProductInfo(@PathVariable Long id, Model model) {
        List<Ingredient> ingredients = ingredientService.getIngredients(id);
        model.addAttribute("ingredients", ingredients);
        return "finished_product_info";
    }

    @GetMapping("/ingredients/create")
    public String createIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "create_ingredient"; // Имя HTML-страницы для создания ингредиента
    }

    @PostMapping("/ingredients/create")
    public String createIngredient(@ModelAttribute Ingredient ingredient) throws NotEnoughMaterialsException {
        ingredientService.createIngredient(ingredient);
        return "redirect:/ingredients"; // Перенаправление на список ингредиентов
    }

    @GetMapping("/ingredients/edit/{id}")
    public String editIngredientForm(@PathVariable Long id, Model model) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        model.addAttribute("ingredient", ingredient);
        return "edit_ingredient"; // Имя HTML-страницы для редактирования ингредиента
    }

    @PostMapping("/ingredients/edit/{id}")
    public String updateIngredient(@PathVariable Long id, @ModelAttribute Ingredient ingredient) {
        ingredient.setId(id); // Установите ID, чтобы обновить существующую запись
        ingredientService.updateIngredient(ingredient);
        return "redirect:/ingredients"; // Перенаправление на список ингредиентов
    }

    @DeleteMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return "redirect:/ingredients"; // Перенаправление на список ингредиентов
    }
}
