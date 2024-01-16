package com.micros.inventory.controller;

import com.micros.inventory.model.Inventory;
import com.micros.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public Boolean isInStock(@PathVariable("skuCode") String skuCode) {
        Inventory inventory = inventoryService.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Cannot find Product by sku code " + skuCode));
        return inventory.getStock() > 0;
    }
}
