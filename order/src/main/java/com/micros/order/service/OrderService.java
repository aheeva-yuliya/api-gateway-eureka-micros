package com.micros.order.service;

import com.micros.order.dto.OrderDto;
import com.micros.order.model.Order;
import com.micros.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final StreamBridge streamBridge;

    public String createOrder(@NotNull OrderDto orderDto) {
        Order order = new Order();
        order.setOrderLineItems(orderDto.getOrderLineItemsList());
        order.setOrderNumber(UUID.randomUUID().toString());

        orderRepository.save(order);

        log.info("Sending Order Details to Notification Service");

        streamBridge.send("notification-events", MessageBuilder.withPayload(order.getId()).build());

        return "Order place successfully";
    }

}
