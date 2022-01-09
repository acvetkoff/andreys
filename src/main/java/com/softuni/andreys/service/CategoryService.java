package com.softuni.andreys.service;

import com.softuni.andreys.models.enums.CategoryType;
import com.softuni.andreys.models.service.CategoryServiceModel;

public interface CategoryService {

    void init();

    CategoryServiceModel findByName(CategoryType name);
}
