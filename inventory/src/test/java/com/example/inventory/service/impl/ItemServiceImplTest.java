package com.example.inventory.service.impl;

import com.example.inventory.entity.Item;
import com.example.inventory.mapper.ItemMapper;
import com.example.inventory.service.ItemCodeDuplicateException;
import com.example.inventory.service.NoSuchItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ItemServiceImplTest {

    private ItemMapper itemMapperMock;
    private ItemServiceImpl target;

    @BeforeEach
    void beforeEach() {
        // モックオブジェクトの生成
        itemMapperMock = mock(ItemMapper.class);

        // テスト対象オブジェクトのセットアップ
        target = new ItemServiceImpl(itemMapperMock);
    }

    /**
     * コードによる部品の1件検索において、該当データが存在していれば値の入った1件のItemオブジェクトを返却する
     *
     * @throws Exception
     */
    @Test
    void testGetByCode_Found() throws Exception {

        // findByCodeメソッドが部品を返す
        when(itemMapperMock.findByCode("10020-A")).thenReturn(new Item("10020-A", "クランクシャフト ベアリング上部ピン"));

        // 期待値
        Item expectedItem = new Item("10020-A", "クランクシャフト ベアリング上部ピン");

        // テスト対象メソッドを実行して実際値を取得
        Item actualItem = target.getByCode("10020-A");

        // 検証
        assertEquals(expectedItem, actualItem);
    }

    /**
     * コードによる部品の1件検索において、該当データが存在しなければNoSuchItemExceptionが発生する
     *
     * @throws Exception
     */
    @Test
    void testGetByCode_NotFound() throws Exception {

        // findByCodeメソッドが部品を返す
        when(itemMapperMock.findByCode("11111")).thenReturn(null);

        assertThrows(NoSuchItemException.class, () -> {
            // テスト対象メソッドを実行して実際値を取得
            Item actualItem = target.getByCode("11111");
        });

    }

    /**
     * 部品全件検索において、データが存在すればコードの昇順で一覧を返す
     *
     * @throws Exception
     */
    @Test
    void testGetAll_Found() throws Exception {

        // getAllメソッドが呼び出されたら部品一覧を返す
        List<Item> allItems = List.of(
                new Item("10010", "クランクシャフト ロッド"),
                new Item("10020-A", "クランクシャフト ベアリング上部ピン"),
                new Item("10020-B", "クランクシャフト ベアリング下部ピン"),
                new Item("10030", "ピストン"),
                new Item("10040", "ピストン・リング")
        );
        when(itemMapperMock.findAll()).thenReturn(allItems);

        // 期待値
        List<Item> expectedItems = List.of(
                new Item("10010", "クランクシャフト ロッド"),
                new Item("10020-A", "クランクシャフト ベアリング上部ピン"),
                new Item("10020-B", "クランクシャフト ベアリング下部ピン"),
                new Item("10030", "ピストン"),
                new Item("10040", "ピストン・リング")
        );

        // テスト対象メソッドを実行して実際値を取得
        List<Item> actualItems = target.getAll();

        // 検証
        assertEquals(expectedItems, actualItems);

    }

    /**
     * 部品全件検索において、データが存在しなければ空のリストを返す（例外は発生しない）
     *
     * @throws Exception
     */
    @Test
    void testGetAll_NotFound() throws Exception {

        // getAllメソッドが呼び出されたら空のリストを返す
        when(itemMapperMock.findAll()).thenReturn(List.of());

        // 期待値（空のリスト）
        List<Item> expectedItems = List.of();

        // テスト対象メソッドを実行して実際値を取得
        List<Item> actualItems = target.getAll();

        // 検証
        assertEquals(expectedItems, actualItems);
    }

    /**
     * 部品コードの重複がない部品を登録するとデータベースのitemsテーブルに行が追加される
     *
     * @throws Exception
     */
    @Test
    void testRegister_OK() throws Exception {

        // existsByIdメソッドがfalseを返す（部品コードに重複がない状態）
        when(itemMapperMock.findByCode("10050")).thenReturn(null);
        // findByCodeメソッドが部品を返す
        when(itemMapperMock.insert(any(Item.class))).thenReturn(1);

        // テスト対象メソッドを実行
        String actualCode = target.register(new Item("10050", "サークリップ"));

        // 検証
        assertEquals("10050", actualCode);
        // itemRepositoryMockが指定の引数で1回実行されていることを確認
        verify(itemMapperMock, times(1)).insert(new Item("10050", "サークリップ"));

    }

    /**
     * 部品登録の際、部品コードに重複があるとItemCodeDuplicateExceptionが発生する
     *
     * @throws Exception
     */
    @Test
    void testRegister_Duplicate() throws Exception {

        // existsByIdメソッドがtrueを返す（部品コードに重複がある状態）
        when(itemMapperMock.findByCode("10020-A")).thenReturn(new Item("10020-A", "クランクシャフト ベアリング上部ピン"));

        assertThrows(ItemCodeDuplicateException.class, () -> {
            // テスト対象メソッドを実行
            String actualCode = target.register(new Item("10020-A", "クランクシャフト ベアリング上部ピン"));
        });

    }

}
