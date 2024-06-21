package com.example.inventory.service;

/**
 * 部品コードが重複している場合にスローされます。
 */
public class ItemCodeDuplicateException extends RuntimeException {

    public ItemCodeDuplicateException() {
        super();
    }

    public ItemCodeDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemCodeDuplicateException(String message) {
        super(message);
    }

    public ItemCodeDuplicateException(Throwable cause) {
        super(cause);
    }

}
