package com.mkolongo.judgesystem.domain.dtos;

import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class ContestImportDto {
    private long id;

    @NotNull(message = "Contest name cannot be empty!")
    @Length(min = 5, message = "Contest name must at least 5 symbols long!")
    @Pattern(regexp = "^[A-Z].*", message = "Contest name must start with capital letter!")
    private String name;

    @NotNull(message = "Category cannot be empty!")
    private CategoryImportDto category;

    @SerializedName(value = "allowedStrategies")
    private Set<StrategyImportDto> strategies;

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

    public CategoryImportDto getCategory() {
        return category;
    }

    public void setCategory(CategoryImportDto category) {
        this.category = category;
    }

    public Set<StrategyImportDto> getStrategies() {
        return strategies;
    }

    public void setStrategies(Set<StrategyImportDto> strategies) {
        this.strategies = strategies;
    }
}
