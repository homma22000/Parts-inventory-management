package com.example.inventory.mapper;

import com.example.inventory.entity.Item;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.io.FileInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Import(DbUnitConfiguration.class)
class ItemMapperTest {

    @Autowired
    private IDatabaseTester tester;

    @Autowired
    private ItemMapper target;

    @BeforeEach
    void setUp() throws Exception {
        // 前提状態の設定
        tester.setDataSet(new XlsDataSet(new FileInputStream("src/test/java/com/example/inventory/mapper/ItemRepositoryTest_default.xlsx")));
        tester.onSetup();

    }

    /**
     * コードによる部品の1件検索において、該当データが存在していれば値の入った1件のItemオブジェクトを返却する
     *
     * @throws Exception
     */
    @Test
    void testFindByCode_Found() throws Exception {

        // 期待値
        Item expectedItem = new Item("10020-A", "クランクシャフト ベアリング上部ピン");

        // テスト対象メソッドを実行して実際値を取得
        Item actualItem = target.findByCode("10020-A");

        // 検証
        assertEquals(expectedItem, actualItem);
    }

    /**
     * コードによる部品の1件検索において、該当データが存在しなければNotFoundExceptionが発生する
     *
     * @throws Exception
     */
    @Test
    void testFindByCode_NotFound() throws Exception {

        // テスト対象メソッドを実行して実際値を取得
        Item actualItem = target.findByCode("11111");

        assertNull(actualItem);

    }

    /**
     * 部品全件検索において、データが存在すればコードの昇順で一覧を返す
     *
     * @throws Exception
     */
    @Test
    void testFindAll_Found() throws Exception {

        // 期待値
        List<Item> expectedItems = List.of(
                new Item("10010", "クランクシャフト ロッド"),
                new Item("10020-A", "クランクシャフト ベアリング上部ピン"),
                new Item("10020-B", "クランクシャフト ベアリング下部ピン"),
                new Item("10030", "ピストン"),
                new Item("10040", "ピストン・リング")
        );

        // テスト対象メソッドを実行して実際値を取得
        List<Item> actualItems = target.findAll();

        // 検証
        assertEquals(expectedItems, actualItems);

    }

    /**
     * 部品全件検索において、データが存在しなければ空のリストを返す（例外は発生しない）
     *
     * @throws Exception
     */
    @Test
    void testFindAll_NotFound() throws Exception {

        // 前提状態の設定
        tester.setDataSet(new XlsDataSet(new FileInputStream("src/test/java/com/example/inventory/mapper/ItemRepositoryTest_nodata.xlsx")));
        tester.onSetup();

        // 期待値（空のリスト）
        List<Item> expectedItems = List.of();

        // テスト対象メソッドを実行して実際値を取得
        List<Item> actualItems = target.findAll();

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

        // 期待値
        IDataSet expectedDataSet = new XlsDataSet(new FileInputStream("src/test/java/com/example/inventory/mapper/ItemRepositoryTest_testRegister_OK_expected.xlsx"));
        ITable expectedItemsTable = expectedDataSet.getTable("items");

        // テスト対象メソッドを実行
        int insertCount = target.insert(new Item("10050", "サークリップ"));

        // 検証
        assertEquals(1, insertCount);
        IDataSet actualDataSet = tester.getConnection().createDataSet();
        ITable actualItemsTable = actualDataSet.getTable("items");
        Assertion.assertEquals(expectedItemsTable, actualItemsTable);

    }

}
