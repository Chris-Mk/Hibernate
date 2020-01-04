package com.mkolongo.judgesystem.services;

import com.mkolongo.judgesystem.domain.dtos.CategoryImportDto;
import com.mkolongo.judgesystem.domain.models.Category;
import com.mkolongo.judgesystem.repositories.CategoryRepository;
import com.mkolongo.judgesystem.services.base.CategoryService;
import com.mkolongo.judgesystem.util.base.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importCategories(CategoryImportDto[] categoryImportDtos) {

        for (CategoryImportDto categoryImportDto : categoryImportDtos) {
            mapSubCategories(categoryImportDto.getSubcategories(), categoryImportDto);
        }
    }

    private void mapSubCategories(Set<CategoryImportDto> subcategories, CategoryImportDto parentCategoryDto) {
        parentCategoryDto.setSubcategories(null);

        if (!validationUtil.isValid(parentCategoryDto)) {
            validationUtil.getViolations(parentCategoryDto)
                    .forEach(violation -> System.out.println(violation.getMessage()));
            return;
        }

        final Category category = modelMapper.map(parentCategoryDto, Category.class);
        categoryRepository.saveAndFlush(category);

        if (subcategories == null) {
            return;
        }

        for (CategoryImportDto subcategoryDto : subcategories) {
            mapSubCategories(subcategoryDto.getSubcategories(), subcategoryDto);
        }
    }
}
