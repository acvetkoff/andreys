package com.softuni.andreys.models.entity;

import com.softuni.andreys.models.enums.CategoryType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private CategoryType name;

    public Category() {
    }

    public Category(CategoryType name) {
        this.name = name;
    }

    @Enumerated(value = EnumType.STRING)
    public CategoryType getName() {
        return this.name;
    }

    public void setName(CategoryType name) {
        this.name = name;
    }
}
