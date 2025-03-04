package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Ingredient;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.IngredientRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient createIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id){
        return ingredientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ingredient not found by id " + id));
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
