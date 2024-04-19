package org.example.project.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class SavingsAccount extends Account{
    private BigDecimal interestRate;

    public SavingsAccount() {
        this.interestRate = BigDecimal.valueOf(0.042);
    }

    public void applyInterest() {
        BigDecimal interest = this.getBalance().multiply(interestRate);
        this.setBalance(this.getBalance().add(interest));
    }
}
