package com.mkolongo.cardealer.models;

public enum Discount {
    ZERO(0),
    FIVE(5),
    TEN(10),
    FIFTEEN(15),
    TWENTY(20),
    THIRTY(30),
    FORTY(40),
    FIFTY(50);

    private int discount;

    Discount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public double getFraction() {
        return discount / 100.0;
    }

    public String getString() {
        return discount + "%";
    }
}
