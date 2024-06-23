package com.example.inventory.mapper;

import com.example.inventory.entity.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface ItemMapper {

    // 部品の一件取得
    @Select("select code, name from items where code=#{code}")
    Item findByCode(String code);

    // 部品の全件取得
    @Select("select code, name from items order by code")
    List<Item> findAll();

    @Insert("insert into items(code, name) values(#{code}, #{name})")
    int insert(Item item);

}
