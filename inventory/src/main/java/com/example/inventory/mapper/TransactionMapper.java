package com.example.inventory.mapper;

import com.example.inventory.entity.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TransactionMapper {

    @Insert("insert into inventory_transactions(type, date_time, item_code, quantity, description)" +
            " values(#{type}, #{dateTime}, #{item.code}, #{quantity}, #{description})")
    int insert(Transaction transaction);
}
