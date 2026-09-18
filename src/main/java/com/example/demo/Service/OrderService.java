package com.example.demo.Service;

import com.example.demo.Model.FoodItem;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderItem;
import com.example.demo.Repository.FoodItemRepository;
import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodItemRepository foodItemRepository;

    public OrderService(OrderRepository orderRepository, FoodItemRepository foodItemRepository) {
        this.orderRepository = orderRepository;
        this.foodItemRepository = foodItemRepository;
    }

    public Order placeOrder(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        double total = 0;
        for (OrderItem item : order.getItems()) {
            if (item.getFoodItem() == null || item.getFoodItem().getId() == null) {
                throw new IllegalArgumentException("Each order item must include a food item");
            }

            FoodItem foodItem = foodItemRepository.findById(item.getFoodItem().getId())
                    .orElseThrow(() -> new RuntimeException("Food item not found: " + item.getFoodItem().getId()));

            item.setFoodItem(foodItem);
            item.setOrder(order);
            double itemTotal = foodItem.getPrice() * item.getQuantity();
            item.setPrice(itemTotal);
            total += itemTotal;
        }

        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
