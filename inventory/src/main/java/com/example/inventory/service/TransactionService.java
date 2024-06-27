package com.example.inventory.service;

import com.example.inventory.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAll(String itemCode);
    void register(Transaction transaction);
    int getCurrentQuantity(String itemCode);
}
