package com.example.demo.services;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.services.base.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> selectIngredientsByName(String name) {
        return this.ingredientRepository.findAllByNameStartingWith(name);
    }

    @Override
    public List<Ingredient> selectIngredientsByNames(List<String> names) {
        return this.ingredientRepository.findAllByNameInOrderByPrice(names);
    }

    @Override
    public void deleteIngredientsByName(String name) {
        this.ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    public void updateAllIngredientsByPrice() {
        this.ingredientRepository.updateAllIngredientsPrice();
    }

    @Override
    public void updateIngredientsPriceByNames(List<String> names) {
        this.ingredientRepository.updateIngredientsPriceByNames(names);
    }
}
