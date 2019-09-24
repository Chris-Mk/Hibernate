package com.mkolongo.productsshop.runners;

import com.mkolongo.productsshop.services.base.CategoryService;
import com.mkolongo.productsshop.services.base.ProductService;
import com.mkolongo.productsshop.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;
    private final Scanner scanner;

    @Autowired
    public ConsoleRunner(CategoryService categoryService, ProductService productService,
                         UserService userService, Scanner scanner) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) throws IOException {
        userService.seedUsers();
        categoryService.seedCategories();
        productService.seedProducts();

//        productsInRange();
//        successfullySoldProducts();
//        categoriesByProductsCount();
        usersAndProducts();
    }

    private void usersAndProducts() {
        System.out.println(productService.getUsersWithSoldProducts());
    }

    private void categoriesByProductsCount() {
        System.out.println(categoryService.getCategoriesOrderedByProductsCount());
    }

    private void successfullySoldProducts() {
        System.out.println(productService.getSoldProducts());
    }

    private void productsInRange() {
        BigDecimal lowerBound = new BigDecimal(scanner.nextLine());
        BigDecimal upperBound = new BigDecimal(scanner.nextLine());

        final String jsonProducts = productService.getProductsWithPriceInRange(lowerBound, upperBound);
        System.out.println(jsonProducts);
    }
}
