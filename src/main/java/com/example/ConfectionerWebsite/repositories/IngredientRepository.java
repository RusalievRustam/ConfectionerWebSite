package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
}
