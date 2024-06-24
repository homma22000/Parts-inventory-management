package com.example.inventory.mapper;

import com.example.inventory.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InventoryMapper {

    @Select("select items.code as code, items.name as name ,sum(inventory_transactions.quantity) as quantity" +
            " from items inner join inventory_transactions on items.code = inventory_transactions.item_code" +
            " group by items.code, items.name")
    List<Inventory> findAll();
}
