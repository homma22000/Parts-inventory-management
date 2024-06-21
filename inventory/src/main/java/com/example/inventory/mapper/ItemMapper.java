package com.example.inventory.mapper;

import com.example.inventory.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * itemsテーブルへの操作を提供します。
 */
@Mapper
public interface ItemMapper {

    /**
     * 指定された部品コードで1件の部品を検索して返します。
     * @param code 部品コード
     * @return 部品エンティティ
     */
    Item findByCode(String code);

    /**
     * 部品を全件検索して返します。
     * @return 部品エンティティのリスト
     */
    List<Item> findAll();

    /**
     * 指定された部品エンティティを登録します。
     * @param item 部品エンティティ
     * @return 登録件数
     */
    int insert(Item item);

}
