package org.example.project.service.impl;

import org.example.project.dto.TransactionDTO;
import org.example.project.dto.UserDTO;
import org.example.project.model.Transaction;
import org.example.project.model.User;
import org.example.project.repository.TransactionRepository;
import org.example.project.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    @Override
    public TransactionDTO addTransaction(Transaction transaction) {
        return convertToDTO(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setDate(transaction.getDate());
        dto.setType(transaction.getType());
        dto.setAmount(transaction.getAmount());
        dto.setAccount(transaction.getAccount());
        return dto;
    }

    @Override
    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public TransactionDTO updateTransaction(Transaction transaction) {
        return convertToDTO(transactionRepository.save(transaction));
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        return convertToDTO(transactionRepository.getReferenceById(id));
    }
}
