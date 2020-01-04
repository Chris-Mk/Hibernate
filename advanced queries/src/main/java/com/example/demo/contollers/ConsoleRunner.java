package com.example.demo.contollers;

import com.example.demo.domain.entities.Shampoo;
import com.example.demo.services.base.IngredientService;
import com.example.demo.services.base.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final IngredientService ingredientService;
    private final Scanner scanner;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService, Scanner scanner) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) {
//        final String shampooSize = this.scanner.nextLine();
//        long labelId = Long.parseLong(scanner.nextLine());

//        final List<Shampoo> shampoos = this.shampooService.getShampoosBySize(shampooSize);
//        this.printResult(shampoos);

//        final List<Shampoo> shampoos = this.shampooService.getShampoosBySizeOrLabel(shampooSize, labelId);
//        this.printResult(shampoos);

//        BigDecimal shampooPrice = new BigDecimal(this.scanner.nextLine());
//
//        final List<Shampoo> shampoos = this.shampooService.selectShampooByPrice(shampooPrice);
//        this.printResult(shampoos);

//        final String ingredientName = this.scanner.nextLine();
//
//        this.ingredientService.selectIngredientsByName(ingredientName)
//                .forEach(ingredient -> System.out.println(ingredient.getName()));

        List<String> ingredientNames = new ArrayList<>();

        while (true) {
            String name = this.scanner.nextLine();

            if (name.isBlank()) break;
            ingredientNames.add(name);
        }
//
//        this.ingredientService.selectIngredientsByNames(ingredientNames)
//                .forEach(ingredient -> System.out.println(ingredient.getName()));


//        System.out.println(this.shampooService.countShampoosByPrice(shampooPrice));

//        this.shampooService.selectShampoosByIngredients(ingredientNames)
//                .forEach(shampoo -> System.out.println(shampoo.getBrand()));

//        int ingredientsCount = Integer.parseInt(scanner.nextLine());
//
//        this.shampooService.selectShampooByIngredientsCount(ingredientsCount)
//                .forEach(shampoo -> System.out.println(shampoo.getBrand()));

//        this.shampooService.selectIngredientNameAndShampooBrandByName()
//                .forEach(System.out::println);

//        this.ingredientService.deleteIngredientsByName(ingredientName);

//        this.ingredientService.updateAllIngredientsByPrice();

        this.ingredientService.updateIngredientsPriceByNames(ingredientNames);
    }

    private void printResult(List<Shampoo> shampoos) {
        shampoos
                .forEach(shampoo -> System.out.println(String.format("%s %s %slv.",
                        shampoo.getBrand(),
                        shampoo.getSize().name(),
                        shampoo.getPrice())));
    }
}
