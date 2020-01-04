package com.mkolongo.judgesystem.services.base;

import com.mkolongo.judgesystem.domain.dtos.CategoryImportDto;

public interface CategoryService {

    void importCategories(CategoryImportDto[] categoryImportDtos);
}
