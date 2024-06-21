package com.example.inventory.service;

import com.example.inventory.entity.Item;

import java.util.List;

/**
 * 部品に関する業務処理を提供します。
 */
public interface ItemService {

    /**
     * 指定された部品コードで1件の部品を検索して返します。
     * @param code 部品コード
     * @return 部品エンティティ
     * @throws NoSuchItemException 指定された部品コードに対応する部品が存在しない場合
     */
    Item getByCode(String code) throws NoSuchItemException;

    /**
     * 全ての部品を返します。
     * @return 部品エンティティのリスト
     */
    List<Item> getAll();

    /**
     * 指定された部品エンティティを登録します。
     * @param item 部品エンティティ
     * @return 登録された部品の部品コード
     * @throws ItemCodeDuplicateException 同じ部品コードの部品が既に存在する場合
     */
    String register(Item item) throws ItemCodeDuplicateException;

}
