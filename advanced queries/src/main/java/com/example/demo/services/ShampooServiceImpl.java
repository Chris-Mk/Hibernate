package com.example.demo.services;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.domain.entities.Shampoo;
import com.example.demo.domain.entities.Size;
import com.example.demo.repositories.ShampooRepository;
import com.example.demo.services.base.IngredientService;
import com.example.demo.services.base.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;
    private final IngredientService ingredientService;
    //private final LabelService labelService;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository, IngredientService ingredientService) {
        this.shampooRepository = shampooRepository;
        this.ingredientService = ingredientService;
        //this.labelService = labelService;
    }

    @Override
    public List<Shampoo> selectShampooBySize(String size) {
        final Size shampooSize = Size.valueOf(size);

        return this.shampooRepository.findAllBySizeOrderById(shampooSize);
    }

    @Override
    public List<Shampoo> selectShampoosBySizeOrLabel(String size, long labelId) {
        final Size shampooSize = Size.valueOf(size);

        return this.shampooRepository.findAllBySizeOrLabelOrderByPrice(shampooSize, labelId);
    }

    @Override
    public List<Shampoo> selectShampooByPrice(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public int countShampoosByPrice(BigDecimal price) {
        return this.shampooRepository.countAllByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> selectShampoosByIngredients(List<String> ingredientsNames) {
        final List<Ingredient> ingredients = this.ingredientService.selectIngredientsByNames(ingredientsNames);

        return this.shampooRepository.findShampoosByIngredientsIn(ingredients);
    }

    @Override
    public List<Shampoo> selectShampooByIngredientsCount(int ingredientsCount) {
        return this.shampooRepository.findShampoosByIngredientsCountLessThan(ingredientsCount);
    }

    @Override
    public List<BigDecimal> selectIngredientNameAndShampooBrandByName() {
        return this.shampooRepository.findShampoosAndIngredientsTotalPrice();
    }
}
