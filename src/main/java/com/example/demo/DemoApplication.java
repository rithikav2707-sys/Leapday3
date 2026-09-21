package com.example.demo;

import com.example.demo.Model.FoodItem;
import com.example.demo.Repository.FoodItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner seedMenu(FoodItemRepository foodItemRepository) {
		return args -> {
			if (foodItemRepository.count() == 0) {
				FoodItem burger = new FoodItem();
				burger.setName("Classic Burger");
				burger.setCategory("Main Course");
				burger.setPrice(249.0);

				FoodItem pizza = new FoodItem();
				pizza.setName("Margherita Pizza");
				pizza.setCategory("Italian");
				pizza.setPrice(329.0);

				FoodItem pasta = new FoodItem();
				pasta.setName("Creamy Pasta");
				pasta.setCategory("Italian");
				pasta.setPrice(289.0);

				FoodItem salad = new FoodItem();
				salad.setName("Garden Salad");
				salad.setCategory("Healthy");
				salad.setPrice(199.0);

				foodItemRepository.saveAll(java.util.List.of(burger, pizza, pasta, salad));
			}
		};
	}

}
