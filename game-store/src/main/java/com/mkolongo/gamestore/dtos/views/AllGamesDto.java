package com.mkolongo.gamestore.dtos.views;

import java.math.BigDecimal;

public class AllGamesDto {
    private String title;
    private BigDecimal price;

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getTitle() + " " + getPrice();
    }
}
