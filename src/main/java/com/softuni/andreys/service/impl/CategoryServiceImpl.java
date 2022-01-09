package com.softuni.andreys.service.impl;

import com.softuni.andreys.models.entity.Category;
import com.softuni.andreys.models.enums.CategoryType;
import com.softuni.andreys.models.service.CategoryServiceModel;
import com.softuni.andreys.repository.CategoryRepository;
import com.softuni.andreys.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.softuni.andreys.models.enums.CategoryType.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @PostConstruct
    public void init() {
        if (this.categoryRepository.count() == 0) {
            Category shirt = new Category(SHIRT);
            Category denim = new Category(DENIM);
            Category shorts = new Category(SHORTS);
            Category jacket = new Category(JACKET);

            this.categoryRepository.saveAll(List.of(shirt, denim, shorts, jacket));
        }
    }

    @Override
    public CategoryServiceModel findByName(CategoryType name) {
        return this.categoryRepository
                .findByName(name)
                .map(category -> this.modelMapper.map(category, CategoryServiceModel.class))
                .orElse(null);
    }
}
