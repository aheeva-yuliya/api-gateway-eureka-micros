package com.micros.inventory.service;

import com.micros.inventory.model.Inventory;
import com.micros.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Optional<Inventory> findBySkuCode(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode);
    }
}
