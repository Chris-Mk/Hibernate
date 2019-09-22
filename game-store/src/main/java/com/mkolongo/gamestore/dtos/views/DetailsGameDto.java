package com.mkolongo.gamestore.dtos.views;

import java.time.LocalDate;

public class DetailsGameDto extends AllGamesDto {
    private String description;
    private LocalDate releaseDate;

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format("Title: %s%nPrice: %s%nDescription: %s%nRelease Date: %s",
                super.getTitle(),
                super.getPrice(),
                getDescription(),
                getReleaseDate());
    }
}
