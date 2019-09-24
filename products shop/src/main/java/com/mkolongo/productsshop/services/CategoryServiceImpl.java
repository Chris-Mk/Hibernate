package com.mkolongo.productsshop.services;

import com.google.gson.Gson;
import com.mkolongo.productsshop.dtos.seedDtos.CategorySeedDto;
import com.mkolongo.productsshop.dtos.viewDtos.CategoryViewDto;
import com.mkolongo.productsshop.models.Category;
import com.mkolongo.productsshop.models.Product;
import com.mkolongo.productsshop.repositories.CategoryRepository;
import com.mkolongo.productsshop.services.base.CategoryService;
import com.mkolongo.productsshop.util.base.FileUtil;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper,
                               Validator validator, FileUtil fileUtil, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() == 0) {
            final String categoryJson = fileUtil.readFile("files/categories.json");

            final CategorySeedDto[] categorySeedDto = gson.fromJson(categoryJson, CategorySeedDto[].class);

            final Set<ConstraintViolation<CategorySeedDto[]>> violations = validator.validate(categorySeedDto);

            if (!violations.isEmpty()) {
                violations.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
                return;
            }

            final Category[] categories = modelMapper.map(categorySeedDto, new TypeToken<Category[]>() {
            }.getType());

            categoryRepository.saveAll(Arrays.asList(categories));
        }
    }

    @Override
    @Transactional
    public String getCategoriesOrderedByProductsCount() {
        Converter<Category, Integer> getProductsCount = new AbstractConverter<>() {
            @Override
            protected Integer convert(Category source) {
                return source.getProducts().size();
            }
        };

        modelMapper.createTypeMap(Category.class, CategoryViewDto.class)
                .addMappings(mapping -> {

                    mapping.map(Category::getName,
                            (destination, value) -> destination.setCategory((String) value));

                    //not working ???
//                    mapping.using(getProductsCount)
//                            .map(source -> source,
//                                    (destination, value) -> destination.setProductCount((int) value));
                });

        String[] json = {""};

        categoryRepository.findAll()
                .stream()
                .sorted((c1, c2) -> Integer.compare(c2.getProducts().size(), c1.getProducts().size()))
                .forEach(category -> {
                    final CategoryViewDto categoryViewDto = modelMapper.map(category, CategoryViewDto.class);

                    BigDecimal totalRevenue = new BigDecimal("0.000000");

                    for (Product product : category.getProducts()) {
                        totalRevenue = totalRevenue.add(product.getPrice());
                    }

                    final BigDecimal averagePrice =
                            totalRevenue.divide(new BigDecimal(category.getProducts().size()), RoundingMode.HALF_DOWN);

                    categoryViewDto.setAveragePrice(averagePrice);
                    categoryViewDto.setProductCount(category.getProducts().size());
                    categoryViewDto.setTotalRevenue(new BigDecimal(
                            new DecimalFormat("0.00")
                                    .format(totalRevenue)));

                    json[0] += gson.toJson(categoryViewDto);
                });
        return json[0];
    }
}
