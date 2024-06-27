package com.example.inventory.mapper;

import com.example.inventory.entity.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("select t.id,  t.date_time, t.item_code, i.name, t.type, t.quantity, t.description" +
            " from inventory_transactions t inner join items i ON t.item_code = i.code where t.item_code = #{itemCode}" +
            " order by t.id")
    List<Transaction> findAll(String itemCode);

    @Insert("insert into inventory_transactions(type, date_time, item_code, quantity, description)" +
            " values(#{type}, #{dateTime}, #{item.code}, #{quantity}, #{description})")
    int insert(Transaction transaction);

    @Select("select sum(quantity) from inventory_transactions where item_code = #{itemCode}")
    int getCurrentQuantity(String itemCode);
}
