package com.softuni.andreys.models.service;

import com.softuni.andreys.models.enums.CategoryType;

public class CategoryServiceModel extends BaseServiceModel {
    private CategoryType name;

    public CategoryServiceModel() {
    }

    public CategoryServiceModel(CategoryType name) {
        this.name = name;
    }

    public CategoryType getName() {
        return this.name;
    }

    public void setName(CategoryType name) {
        this.name = name;
    }
}
