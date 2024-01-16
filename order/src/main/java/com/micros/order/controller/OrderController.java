package com.micros.order.controller;

import com.micros.order.client.InventoryClient;
import com.micros.order.dto.OrderDto;
import com.micros.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final InventoryClient inventoryClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto) {
        CircuitBreaker resilience4JCircuitBreaker = circuitBreakerFactory.create("inventory");

        Supplier<Boolean> booleanSupplier = () -> orderDto.getOrderLineItemsList().stream()
                .allMatch(orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));

        boolean allProductsInStock = resilience4JCircuitBreaker.run(booleanSupplier, throwable -> false);

        if (allProductsInStock) {
            return orderService.createOrder(orderDto);
        } else {
            return "Order failed. One of the products is not in stock";
        }
    }
}
