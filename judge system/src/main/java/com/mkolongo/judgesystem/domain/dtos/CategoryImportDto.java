package com.mkolongo.judgesystem.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class CategoryImportDto {

    private long id;

    @NotNull(message = "Category name cannot be empty!")
    @Length(min = 5, message = "Category name must at least 5 symbols long!")
    @Pattern(regexp = "^[A-Z].*", message = "Category name must start with capital letter!")
    private String name;

    @SerializedName(value = "category")
    @Expose(deserialize = false)
    private CategoryImportDto parentCategory;

    @SerializedName(value = "categories")
    @Expose(deserialize = false)
    private Set<CategoryImportDto> subcategories;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryImportDto getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryImportDto parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<CategoryImportDto> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<CategoryImportDto> subcategories) {
        this.subcategories = subcategories;
    }
}
