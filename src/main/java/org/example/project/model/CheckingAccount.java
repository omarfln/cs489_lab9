package org.example.project.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class CheckingAccount extends Account{
    private BigDecimal overdraftLimit;

    public CheckingAccount() {
        this.overdraftLimit = BigDecimal.valueOf(1000.00);
    }

    public boolean canWithdraw(BigDecimal amount) {
        return this.getBalance().add(overdraftLimit).compareTo(amount) >= 0;
    }
}
