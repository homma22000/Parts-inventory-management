package com.example.inventory.service;

import com.example.inventory.entity.Transaction;

public interface TransactionService {

    void register(Transaction transaction);

    int getCurrentQuantity(String itemCode);
}
