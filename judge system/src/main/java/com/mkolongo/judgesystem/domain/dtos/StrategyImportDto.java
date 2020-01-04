package com.mkolongo.judgesystem.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StrategyImportDto {

    @Expose(deserialize = false)
    private long id;

    @NotNull(message = "Strategy name cannot be empty!")
    @Pattern(regexp = "^[A-Z].*", message = "Strategy name must start with capital letter!")
    private String name;

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
}
