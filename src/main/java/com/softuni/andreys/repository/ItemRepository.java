package com.softuni.andreys.repository;

import com.softuni.andreys.models.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Item findByName(String name);
}
