package com.example.inventory.service.impl;

import com.example.inventory.entity.Transaction;
import com.example.inventory.mapper.TransactionMapper;
import com.example.inventory.service.InventoryQuantityShortageException;
import com.example.inventory.service.ItemService;
import com.example.inventory.service.NoSuchItemException;
import com.example.inventory.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional
    public void register(Transaction transaction) throws NoSuchItemException, InventoryQuantityShortageException {

        if ("ISSUE".equals(transaction.getType())) {
            int currentQuantity = getCurrentQuantity(transaction.getItem().getCode());
            if (currentQuantity + transaction.getQuantity() < 0) {
                throw new InventoryQuantityShortageException(currentQuantity, transaction.getQuantity());
            }
        }

        transactionMapper.insert(transaction);
    }

    @Transactional(readOnly = true)
    @Override
    public int getCurrentQuantity(String itemCode) {
        return transactionMapper.getCurrentQuantity(itemCode);
    }
}
