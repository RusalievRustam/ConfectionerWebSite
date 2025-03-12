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
        final var ingredient = new Ingredient();
        ingredient.setFinishedProduct(productService.getFinishedProductById(model.getProductId()));
        ingredient.setRawMaterial(materialService.getMaterialByName(model.getIngredient()));
        ingredient.setQuantity(model.getQuantity());
        return ingredient;
    }


}
