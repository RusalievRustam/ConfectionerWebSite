package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    List<Ingredient> getByFinishedProductId(Long finishedProductId);
}
