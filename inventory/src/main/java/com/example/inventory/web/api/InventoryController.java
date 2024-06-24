package com.example.inventory.web.api;

import com.example.inventory.entity.Inventory;
import com.example.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@CrossOrigin(origins = {"http://localhost:5173"})
public class InventoryController {
    private final InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @GetMapping
    public List<Inventory> getAll() {
        return inventoryService.getAll();
    }
}
