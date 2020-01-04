package com.example.demo.services.base;

import com.example.demo.domain.entities.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> selectIngredientsByName(String name);

    List<Ingredient> selectIngredientsByNames(List<String> names);

    void deleteIngredientsByName(String name);

    void updateAllIngredientsByPrice();

    void updateIngredientsPriceByNames(List<String> names);
}
