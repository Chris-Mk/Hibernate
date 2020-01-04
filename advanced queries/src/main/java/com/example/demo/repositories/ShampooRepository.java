package com.example.demo.repositories;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.domain.entities.Shampoo;
import com.example.demo.domain.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findAllBySizeOrderById(Size size);

    @Query("select s from com.example.demo.domain.entities.Shampoo as s" +
            " where s.size = ?1 or s.label.id = ?2 order by s.price")
    List<Shampoo> findAllBySizeOrLabelOrderByPrice(Size size, long labelId);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countAllByPriceLessThan(BigDecimal price);

    List<Shampoo> findShampoosByIngredientsIn(List<Ingredient> ingredients);

    @Query("select s from com.example.demo.domain.entities.Shampoo as s where size(s.ingredients) < ?1")
    List<Shampoo> findShampoosByIngredientsCountLessThan(int ingredientsCount);

    @Query("select sum(i.price) from com.example.demo.domain.entities.Shampoo as s join s.ingredients as i group by s.id")
    List<BigDecimal> findShampoosAndIngredientsTotalPrice();
}
