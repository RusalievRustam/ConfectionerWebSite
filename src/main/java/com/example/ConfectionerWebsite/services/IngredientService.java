package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Ingredient;
import com.example.ConfectionerWebsite.entities.RawMaterial;
import com.example.ConfectionerWebsite.exceptions.NotEnoughMaterialsException;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.mapping.IngredientsMapping;
import com.example.ConfectionerWebsite.model.IngredientModel;
import com.example.ConfectionerWebsite.repositories.IngredientRepository;
import com.example.ConfectionerWebsite.repositories.RawMaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {

    private IngredientRepository ingredientRepository;
    private RawMaterialRepository rawMaterialRepository;
    private IngredientsMapping ingredientsMapping;

    public Ingredient createIngredient(Ingredient ingredient) throws NotEnoughMaterialsException {
        // Ищем сырье по названию
        RawMaterial rawMaterial = rawMaterialRepository.findByName(ingredient.getRawMaterial().getName());
        if (rawMaterial == null) {
            throw new NotEnoughMaterialsException("There is no such material in store. You should restore.");
        }
        if (rawMaterial.getQuantity() < ingredient.getQuantity()) {
            throw new NotEnoughMaterialsException("Not enough raw materials. You should restore.");
        }

        // Ищем ингредиент по продукту и названию сырья
        Ingredient existingIngredient = ingredientRepository
                .findByFinishedProductAndRawMaterialName(ingredient.getFinishedProduct(), rawMaterial.getName());

        if (existingIngredient != null) {
            // Увеличиваем количество существующего ингредиента
            existingIngredient.setQuantity(existingIngredient.getQuantity() + ingredient.getQuantity());
            ingredient = existingIngredient;
        } else {
            // Привязываем найденное сырье к новому ингредиенту
            ingredient.setRawMaterial(rawMaterial);
        }

        // Обновляем количество сырья
        rawMaterial.setQuantity(rawMaterial.getQuantity() - ingredient.getQuantity());
        rawMaterialRepository.save(rawMaterial);

        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ingredient not found by id " + id));
    }

    public List<Ingredient> getIngredients(Long finishedProductId) {
        return ingredientRepository.getByFinishedProductId(finishedProductId);
    }

    public Ingredient updateIngredient(Ingredient updatedIngredient) {
        Ingredient existingIngredient = getIngredientById(updatedIngredient.getId());
        existingIngredient.setFinishedProduct(updatedIngredient.getFinishedProduct());
        existingIngredient.setRawMaterial(updatedIngredient.getRawMaterial());
        existingIngredient.setQuantity(updatedIngredient.getQuantity());
        return ingredientRepository.save(existingIngredient);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    public void saveAll(List<IngredientModel> ingredientModels) {
        ingredientModels.forEach(model -> {
            final var ingredient = ingredientsMapping.map(model);
            ingredientRepository.save(ingredient);
        });
    }
}
