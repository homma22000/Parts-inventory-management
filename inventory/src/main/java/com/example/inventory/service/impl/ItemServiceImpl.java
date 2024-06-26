package com.example.inventory.service.impl;

import com.example.inventory.entity.Item;
import com.example.inventory.mapper.ItemMapper;
import com.example.inventory.service.ItemCodeDuplicateException;
import com.example.inventory.service.ItemService;
import com.example.inventory.service.NoSuchItemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Item getByCode(String code) throws NoSuchItemException {
        if (itemMapper.findByCode(code) == null) {
            throw new NoSuchItemException();
        }
        return itemMapper.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getAll() {
        return itemMapper.findAll();
    }

    @Override
    @Transactional
    public String register(Item item) throws ItemCodeDuplicateException {
        if(itemMapper.findByCode(item.getCode()) != null){
            throw new ItemCodeDuplicateException();
        }
        itemMapper.insert(item);
        return item.getCode();
    }

}
