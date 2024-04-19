package org.example.project.service;

import org.example.project.dto.TransactionDTO;
import org.example.project.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    TransactionDTO addTransaction(Transaction transaction);
    List<TransactionDTO> getAllTransactions();
    void deleteTransactionById(Long id);
    TransactionDTO updateTransaction(Transaction transaction);

    TransactionDTO getTransactionById(Long id);
}
