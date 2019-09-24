package com.mkolongo.productsshop.services.base;

import com.mkolongo.productsshop.models.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    void seedCategories() throws IOException;

    String getCategoriesOrderedByProductsCount();
}
