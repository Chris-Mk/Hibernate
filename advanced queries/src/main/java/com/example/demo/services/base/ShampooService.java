package com.example.demo.services.base;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.domain.entities.Shampoo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {

    List<Shampoo> selectShampooBySize(String size);

    List<Shampoo> selectShampoosBySizeOrLabel(String size, long labelId);

    List<Shampoo> selectShampooByPrice(BigDecimal price);

    int countShampoosByPrice(BigDecimal price);

    List<Shampoo> selectShampoosByIngredients(List<String> ingredientsNames);

    List<Shampoo> selectShampooByIngredientsCount(int ingredientsCount);

    List<BigDecimal> selectIngredientNameAndShampooBrandByName();
}
