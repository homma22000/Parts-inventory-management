package com.example.inventory.service.impl;

import com.example.inventory.entity.Inventory;
import com.example.inventory.mapper.InventoryMapper;
import com.example.inventory.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryMapper inventoryMapper;

    public InventoryServiceImpl(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Inventory> findAll() {
        return inventoryMapper.findAll();
    }
}
