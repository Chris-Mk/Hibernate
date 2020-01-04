package com.example.demo.repositories;

import com.example.demo.domain.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByNameStartingWith(String letters);

    List<Ingredient> findAllByNameInOrderByPrice(List<String> names);

    @Modifying
    @Transactional
    @Query("delete from com.example.demo.domain.entities.Ingredient as i where i.name = ?1")
    void deleteIngredientByName(String name);

    @Modifying
    @Transactional
    @Query("update com.example.demo.domain.entities.Ingredient as i set i.price = i.price * 1.10")
    void updateAllIngredientsPrice();

    @Modifying
    @Transactional
    @Query("update com.example.demo.domain.entities.Ingredient as i set i.price = i.price * 1.10 where i.name in (:ingredientNames)")
    void updateIngredientsPriceByNames(@Param(value = "ingredientNames") List<String> ingredientsNames);
}
