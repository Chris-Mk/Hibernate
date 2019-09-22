package com.mkolongo.gamestore.dtos.views;

public class OwnedGamesDto {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
