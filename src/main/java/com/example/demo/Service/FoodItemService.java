package com.example.demo.Service;

import com.example.demo.Model.FoodItem;
import com.example.demo.Repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem addFoodItem(FoodItem foodItem) {
        return repository.save(foodItem);
    }

    public List<FoodItem> getFoodItems() {
        return repository.findAll();
    }

    public FoodItem getFoodItemById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Food item not found"));
    }

    public void deleteFoodItem(Long id) {
        repository.deleteById(id);
    }
}
