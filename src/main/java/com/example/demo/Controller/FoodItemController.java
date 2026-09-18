package com.example.demo.Controller;

import com.example.demo.Model.FoodItem;
import com.example.demo.Service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/foods")
public class FoodItemController {

    private final FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    @PostMapping
    public FoodItem addFoodItem(@RequestBody FoodItem foodItem) {
        return service.addFoodItem(foodItem);
    }

    @GetMapping
    public List<FoodItem> getFoodItems() {
        return service.getFoodItems();
    }

    @GetMapping("/{id}")
    public FoodItem getFoodItemById(@PathVariable Long id) {
        return service.getFoodItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable Long id, @RequestBody FoodItem updatedFood) {
        FoodItem existing = service.getFoodItemById(id);
        existing.setName(updatedFood.getName());
        existing.setCategory(updatedFood.getCategory());
        existing.setPrice(updatedFood.getPrice());
        return ResponseEntity.ok(service.addFoodItem(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodItem(@PathVariable Long id) {
        service.deleteFoodItem(id);
        return ResponseEntity.noContent().build();
    }
}
