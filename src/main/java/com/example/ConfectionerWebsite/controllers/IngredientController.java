package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.FinishedProduct;
import com.example.ConfectionerWebsite.entities.Ingredient;
import com.example.ConfectionerWebsite.entities.RawMaterial;
import com.example.ConfectionerWebsite.exceptions.NotEnoughMaterialsException;
import com.example.ConfectionerWebsite.model.IngredientModel;
import com.example.ConfectionerWebsite.services.FinishedProductService;
import com.example.ConfectionerWebsite.services.IngredientService;
import com.example.ConfectionerWebsite.services.RawMaterialService;
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
    private final FinishedProductService productService;
    private final RawMaterialService rawMaterialService;

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
        model.addAttribute("productId", id);
        return "finished_product_info";
    }

    @GetMapping("/ingredients/create")
    public String createIngredientForm(Model model) {
        List<FinishedProduct> finishedProducts = productService.getAllProducts();
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterials();
        model.addAttribute("finishedProducts", finishedProducts);
        model.addAttribute("rawMaterials", rawMaterials);
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
        List<FinishedProduct> finishedProducts = productService.getAllProducts();
        List<RawMaterial> rawMaterials = rawMaterialService.getAllRawMaterials();
        model.addAttribute("finishedProducts", finishedProducts);
        model.addAttribute("rawMaterials", rawMaterials);
        Ingredient ingredient = ingredientService.getIngredientById(id);
        model.addAttribute("ingredient", ingredient);
        return "edit_ingredient"; // Имя HTML-страницы для редактирования ингредиента
    }

    @PostMapping("/ingredients/edit/{id}")
    public String updateIngredient(@PathVariable Long id, @ModelAttribute Ingredient ingredient) {
        final var productId = ingredient.getFinishedProduct().getId();
        ingredient.setId(id); // Установите ID, чтобы обновить существующую запись
        ingredientService.updateIngredient(ingredient);
        return String.format("redirect:/ingredients/%s", productId);
    }

    @GetMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        final var productId = ingredientService.getIngredientById(id).getFinishedProduct().getId();
        ingredientService.deleteIngredient(id);
        return String.format("redirect:/ingredients/%s", productId);
    }

    @GetMapping("/ingredients/save/productId={productId}")
    public String createIngredientsForProduct(Model model, @PathVariable Long productId) {
        model.addAttribute("productId", productId);
        final var productName = productService.getFinishedProductById(productId).getName();
        model.addAttribute("productName", productName);
        return "create_ingredients";
    }

    @PostMapping("/ingredients/save")
    public String saveIngredients(@RequestBody List<IngredientModel> ingredientsModel) {
        ingredientService.saveAll(ingredientsModel);
        final long id = ingredientsModel.get(0).getProductId();
        return String.format("redirect:/ingredients/%s", id);
    }
}