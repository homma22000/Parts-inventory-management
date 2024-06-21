package com.example.inventory.service;

import java.util.Objects;

/**
 * 部品在庫が不足している場合にスローされます。
 */
public class InventoryQuantityShortageException extends RuntimeException {

    /**
     * 実在庫数
     */
    private long realQuantity;

    /**
     * 取引在庫数
     */
    private long transactionQuantity;

    public InventoryQuantityShortageException(long realQuantity, long transactionQuantity) {
        this.realQuantity = realQuantity;
        this.transactionQuantity = transactionQuantity;
    }

    @Override
    public String getLocalizedMessage() {
        return "Inventory quantity is short.";
    }

    @Override
    public String getMessage() {
        return String.format(
                "Real quantity is %d but transaction quantity is %d.",
                realQuantity,
                transactionQuantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryQuantityShortageException that = (InventoryQuantityShortageException) o;
        return realQuantity == that.realQuantity &&
                transactionQuantity == that.transactionQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(realQuantity, transactionQuantity);
    }
}
