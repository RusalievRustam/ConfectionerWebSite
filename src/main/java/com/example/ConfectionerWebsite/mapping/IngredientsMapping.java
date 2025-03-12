package com.example.ConfectionerWebsite.mapping;

import com.example.ConfectionerWebsite.entities.Ingredient;
import com.example.ConfectionerWebsite.model.IngredientModel;
import com.example.ConfectionerWebsite.services.FinishedProductService;
import com.example.ConfectionerWebsite.services.RawMaterialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IngredientsMapping {

    private FinishedProductService productService;
    private RawMaterialService materialService;

    public Ingredient map(IngredientModel model) {
        // Создаем новый объект Ingredient
        final var ingredient = new Ingredient();

        // Получаем законченный продукт по идентификатору из модели и устанавливаем его в ингредиент
        ingredient.setFinishedProduct(productService.getFinishedProductById(model.getProductId()));

        // Получаем сырье по имени из модели и устанавливаем его в ингредиент
        ingredient.setRawMaterial(materialService.getMaterialByName(model.getIngredient()));

        // Устанавливаем количество из модели в ингредиент
        ingredient.setQuantity(model.getQuantity());

        // Возвращаем готовый объект ингредиента
        return ingredient;
    }

}
