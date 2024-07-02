package com.example.inventory.service;

import com.example.inventory.entity.Transaction;

import java.util.List;

public interface StocktakingService {
    void register(List<Transaction> transactions);
}
