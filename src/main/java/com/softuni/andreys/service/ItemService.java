package com.softuni.andreys.service;

import com.softuni.andreys.models.binding.ItemAddBindingModel;
import com.softuni.andreys.models.service.ItemServiceModel;
import com.softuni.andreys.models.view.ItemViewModel;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    ItemServiceModel create(ItemAddBindingModel itemAddBindingModel);

    List<ItemViewModel> findAllItems();

    ItemViewModel findById(String id);

    void delete(String id);
}
