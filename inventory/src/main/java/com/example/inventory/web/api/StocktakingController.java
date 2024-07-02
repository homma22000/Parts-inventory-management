package com.example.inventory.web.api;

import com.example.inventory.entity.Transaction;
import com.example.inventory.service.StocktakingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocktakings")
@CrossOrigin(origins = {"http://localhost:5173"})
public class StocktakingController {

    private final StocktakingService stocktakingService;

    public StocktakingController(StocktakingService stocktakingService) {
        this.stocktakingService = stocktakingService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody List<Transaction> transactions) {
            stocktakingService.register(transactions);
            return ResponseEntity.ok().build();
    }
}
