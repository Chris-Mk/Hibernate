package com.mkolongo.gamestore.dtos;

public class GameDeleteDto {
    private Long id;

    public GameDeleteDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
