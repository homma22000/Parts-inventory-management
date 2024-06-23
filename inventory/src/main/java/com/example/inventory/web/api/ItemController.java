package com.example.inventory.web.api;

import com.example.inventory.entity.Item;
import com.example.inventory.service.ItemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public Item getByCode(@PathVariable String code) { return itemService.getByCode(code); }

    @GetMapping
    public List<Item> getAll() {
        return itemService.getAll();
    }


    @PostMapping
    public ResponseEntity<Void> register(@Validated @RequestBody Item item) {
        itemService.register(item);

        // Locationヘッダーの設定
        URI location = URI.create(String.format("http://localhost:8080/api/items/%d", item.getCode()));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
