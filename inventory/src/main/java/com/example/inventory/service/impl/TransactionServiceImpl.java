package com.example.inventory.service.impl;

import com.example.inventory.entity.Transaction;
import com.example.inventory.mapper.TransactionMapper;
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
    public void register(Transaction transaction) {
        transactionMapper.insert(transaction);
    }
}
