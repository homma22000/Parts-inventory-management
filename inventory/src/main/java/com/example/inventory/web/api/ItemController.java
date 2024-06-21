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
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 指定された部品コードで1件の部品を検索して返します。
     * @param code 部品コード
     * @return 部品
     */
    public Item getByCode(String code) {
        // TODO 未実装
        return null;
    }

    /**
     * 全ての部品を返します。
     * @return
     */
    @GetMapping
    public List<Item> getAll() {
        return itemService.getAll();
    }

    /**
     * リクエストされた部品を登録します。
     * @param item 部品リクエスト
     * @return
     */
    public ResponseEntity<Void> register(@Validated @RequestBody Item item) {
        // TODO 未実装
        return null;
    }
}
