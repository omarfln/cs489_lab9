package org.example.project.controller;
import org.example.project.dto.AccountDTO;
import org.example.project.model.Account;
import org.example.project.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mbweb/api/v1/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<AccountDTO>> listAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> registerAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.addAccount(account));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        account.setId(id);
        return ResponseEntity.ok(accountService.updateAccount(account));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.ok().build();
    }
}


