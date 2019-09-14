package com.mkolongo.bookshopsystem.services;

import com.mkolongo.bookshopsystem.entities.Category;
import com.mkolongo.bookshopsystem.repositories.CategoryRepository;
import com.mkolongo.bookshopsystem.services.base.CategoryService;
import com.mkolongo.bookshopsystem.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String FILE_PATH = System.getProperty("user.dir")
            + "\\src\\main\\resources\\files\\categories.txt";

    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() != 0) return;

        final String[] categoriesContent = fileUtil.getFileContent(FILE_PATH);

        for (String categoryName : categoriesContent) {
            Category category = new Category();
            category.setName(categoryName);

            this.categoryRepository.saveAndFlush(category);
        }
    }
}
