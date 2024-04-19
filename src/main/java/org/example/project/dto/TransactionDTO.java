package org.example.project.dto;

import lombok.Data;
import org.example.project.model.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private String type;
    private BigDecimal amount;
    private LocalDateTime date;
    private Account account;
}
