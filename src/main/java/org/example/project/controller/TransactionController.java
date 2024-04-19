package org.example.project.controller;
import org.example.project.dto.TransactionDTO;
import org.example.project.model.Transaction;
import org.example.project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mbweb/api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody Transaction transaction) {
        TransactionDTO createdTransaction = transactionService.addTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        TransactionDTO updatedTransaction = transactionService.updateTransaction(transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.ok().build();
    }
}
