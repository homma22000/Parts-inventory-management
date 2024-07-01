package com.example.inventory.web.api;

import com.example.inventory.entity.Transaction;
import com.example.inventory.service.TransactionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/transactions")
@CrossOrigin(origins = {"http://localhost:5173"})
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAll(@RequestParam String itemCode) {
        List<Transaction> transactions = transactionService.getAll(itemCode);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> register(@Validated @RequestBody Transaction transaction) {
        transactionService.register(transaction);

        URI location = URI.create(String.format("http://localhost:8080/api/transactions/%d", transaction.getId()));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
