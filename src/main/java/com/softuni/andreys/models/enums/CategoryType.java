package com.softuni.andreys.models.enums;

public enum CategoryType {
    SHIRT("Shirt"),
    DENIM("Denim"),
    SHORTS("Shorts"),
    JACKET("Jacket");

    private String value;

    CategoryType(String value) {
        this.value = value;
    }


    public String getValue() {
        return this.value;
    }
}
