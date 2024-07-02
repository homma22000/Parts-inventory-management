package com.example.inventory.service.impl;

import com.example.inventory.entity.Transaction;
import com.example.inventory.mapper.TransactionMapper;
import com.example.inventory.service.InventoryQuantityShortageException;
import com.example.inventory.service.StocktakingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StocktakingServiceImpl implements StocktakingService {

    private final TransactionMapper transactionMapper;

    public StocktakingServiceImpl(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }
    @Override
    @Transactional
    public void register(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            int currentQuantity = transactionMapper.getCurrentQuantity(transaction.getItem().getCode());
            if (currentQuantity - transaction.getQuantity() < 0) {
                throw new InventoryQuantityShortageException(currentQuantity, transaction.getQuantity());
            }
        }

        for (Transaction transaction : transactions) {
            transactionMapper.insert(transaction);
        }
    }
}
