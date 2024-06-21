package com.example.inventory.mapper;

import com.example.inventory.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {

    // 部品の一件取得
    Item findByCode(String code);

    // 部品の全件取得
    @Select("select code, name from items order by code")
    List<Item> findAll();

    int insert(Item item);

}
