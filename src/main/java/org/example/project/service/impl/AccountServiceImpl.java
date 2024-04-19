package org.example.project.service.impl;

import org.example.project.dto.AccountDTO;
import org.example.project.dto.UserDTO;
import org.example.project.model.Account;
import org.example.project.model.CheckingAccount;
import org.example.project.model.SavingsAccount;
import org.example.project.model.User;
import org.example.project.repository.AccountRepository;
import org.example.project.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public AccountDTO addAccount(Account account) {
        return convertToDTO(accountRepository.save(account));
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDTO updateAccount(Account account) {
        return convertToDTO(accountRepository.save(account));
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AccountDTO convertToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBalance(account.getBalance());
        User user = account.getUser();
        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getAddress());
        dto.setUserDto(userDTO);
        return dto;
    }

    @Override
    public void processEndOfMonth() {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).applyInterest();
                accountRepository.save(account);
            }
        }
    }

    @Override
    public boolean makeWithdrawal(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
        if (account instanceof CheckingAccount && ((CheckingAccount) account).canWithdraw(amount)) {
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);
            return true;
        } else if (!(account instanceof CheckingAccount) && account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        return convertToDTO(accountRepository.getReferenceById(id));
    }
}
