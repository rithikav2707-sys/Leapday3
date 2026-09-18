package com.example.demo;

import com.example.demo.Model.FoodItem;
import com.example.demo.Repository.FoodItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FoodOrderingApplicationTests {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Test
    void shouldSaveFoodItem() {
        FoodItem item = new FoodItem();
        item.setName("Burger");
        item.setPrice(9.99);
        item.setCategory("Main Course");

        FoodItem saved = foodItemRepository.save(item);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Burger");
    }
}
