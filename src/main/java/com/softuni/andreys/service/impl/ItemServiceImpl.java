package com.softuni.andreys.service.impl;

import com.softuni.andreys.models.binding.ItemAddBindingModel;
import com.softuni.andreys.models.entity.Category;
import com.softuni.andreys.models.entity.Item;
import com.softuni.andreys.models.enums.CategoryType;
import com.softuni.andreys.models.enums.Gender;
import com.softuni.andreys.models.service.CategoryServiceModel;
import com.softuni.andreys.models.service.ItemServiceModel;
import com.softuni.andreys.models.view.ItemViewModel;
import com.softuni.andreys.repository.ItemRepository;
import com.softuni.andreys.service.CategoryService;
import com.softuni.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public ItemServiceModel create(ItemAddBindingModel itemAddBindingModel) {
        ItemServiceModel itemServiceModel = this.modelMapper.map(itemAddBindingModel, ItemServiceModel.class);
        CategoryServiceModel categoryServiceModel =
                this.categoryService.findByName(
                        CategoryType.valueOf(itemAddBindingModel.getCategory())
                );

        itemServiceModel.setCategory(categoryServiceModel);

        Item item = this.modelMapper.map(itemServiceModel, Item.class);
        this.itemRepository.save(item);

        return this.modelMapper.map(item, ItemServiceModel.class);
    }

    @Override
    public List<ItemViewModel> findAllItems() {
        return this.itemRepository
                .findAll()
                .stream()
                .map(this::mapItem)
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewModel findById(String id) {
        Item item = this.itemRepository.findById(id).orElse(null);

        if (item != null) {
            return this.mapItem(item);
        }

        return null;
    }

    @Override
    public void delete(String id) {
        this.itemRepository.deleteById(id);
    }

    private ItemViewModel mapItem(Item item) {
        ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);
        itemViewModel.setImageUrl(this.createImageUrl(item.getGender(), item.getCategory().getName().name()));

        return itemViewModel;
    }

    private String createImageUrl(Gender gender, String name) {
        return String.format("/img/%s-%s.jpg", gender.name(), name);
    }
}
