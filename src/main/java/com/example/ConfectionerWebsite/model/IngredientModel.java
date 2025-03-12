package com.example.ConfectionerWebsite.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class IngredientModel {
    private long productId;
    private String ingredient;
    private double quantity;
}
