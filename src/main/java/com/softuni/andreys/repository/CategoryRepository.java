package com.softuni.andreys.repository;

import com.softuni.andreys.models.entity.Category;
import com.softuni.andreys.models.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(CategoryType name);
}
