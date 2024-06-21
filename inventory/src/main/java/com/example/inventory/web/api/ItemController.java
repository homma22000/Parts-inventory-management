package com.example.inventory.web.api;

import com.example.inventory.entity.Item;
import com.example.inventory.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * 部品を操作するWeb APIを提供します。
 */
@RestController
@RequestMapping("/api/items")
@CrossOrigin
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{code}")
    public Item getByCode(@PathVariable String code) {
        return null;
    }

    @GetMapping
    public List<Item> getAll() {
        return itemService.getAll();
    }


    public ResponseEntity<Void> register(@Validated @RequestBody Item item) {
        return null;
    }
}
