package com.mkolongo.gamestore.dtos;

import com.mkolongo.gamestore.dtos.base.BaseGameDto;

public class GameEditDto extends BaseGameDto {
    private Long id;
    private String[] params;

    public GameEditDto(Long id, String[] parameters) {
        this.id = id;
        this.params = parameters;
    }

    public Long getId() {
        return id;
    }

    public String[] getParams() {
        return params;
    }
}
