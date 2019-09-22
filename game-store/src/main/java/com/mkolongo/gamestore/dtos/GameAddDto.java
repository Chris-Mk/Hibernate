package com.mkolongo.gamestore.dtos;

import com.mkolongo.gamestore.dtos.base.BaseGameDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddDto extends BaseGameDto {

    public GameAddDto(String title, BigDecimal price, double size, String trailer, String imageThumbnail, String description, LocalDate releaseDate) {
        super(title, price, size, trailer, imageThumbnail, description, releaseDate);
    }
}
