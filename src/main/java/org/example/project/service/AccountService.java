package org.example.project.service;

import org.example.project.dto.AccountDTO;
import org.example.project.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
    AccountDTO addAccount(Account account);
    void deleteAccountById(Long id);
    AccountDTO updateAccount(Account account);
    List<AccountDTO> getAllAccounts();
    void processEndOfMonth();
    boolean makeWithdrawal(Long accountId, BigDecimal amount);
    AccountDTO getAccountById(Long id);
}
