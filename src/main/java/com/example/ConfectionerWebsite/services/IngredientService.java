package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Ingredient;
import com.example.ConfectionerWebsite.entities.RawMaterial;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.IngredientRepository;
import com.example.ConfectionerWebsite.repositories.RawMaterialRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Index;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {

    private IngredientRepository ingredientRepository;
    private RawMaterialRepository rawMaterialRepository;

    public Ingredient createIngredient(Ingredient ingredient){
        RawMaterial rawMaterial = rawMaterialRepository.findByName(ingredient.getRawMaterial().getName());
        rawMaterial.setQuantity(rawMaterial.getQuantity() - ingredient.getQuantity());
        rawMaterialRepository.save(rawMaterial);
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id){
        return ingredientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ingredient not found by id " + id));
    }

    public List<Ingredient> getIngredients(Long finishedProductId){
        List<Ingredient> ingredients = ingredientRepository.getByFinishedProductId(finishedProductId);
        return ingredients;
    }

    public Ingredient updateIngredient( Ingredient updatedIngredient){
        Ingredient existingIngredient = getIngredientById(updatedIngredient.getId());
        existingIngredient.setFinishedProduct(updatedIngredient.getFinishedProduct());
        existingIngredient.setRawMaterial(updatedIngredient.getRawMaterial());
        existingIngredient.setQuantity(updatedIngredient.getQuantity());
        return ingredientRepository.save(existingIngredient);
    }

    public void deleteIngredient(Long id){
        ingredientRepository.deleteById(id);
    }
}
