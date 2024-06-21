package com.example.inventory.service;

/**
 * 存在しない部品が指定された場合にスローされます。
 */
public class NoSuchItemException extends RuntimeException {

    public NoSuchItemException() {
        super();
    }

    public NoSuchItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchItemException(String message) {
        super(message);
    }

    public NoSuchItemException(Throwable cause) {
        super(cause);
    }

}
