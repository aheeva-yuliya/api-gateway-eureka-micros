package com.micros.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory")
public interface InventoryClient {
    @GetMapping("/api/inventory/{skuCode}")
    Boolean checkStock(@PathVariable(value = "skuCode") String skuCode);
}
