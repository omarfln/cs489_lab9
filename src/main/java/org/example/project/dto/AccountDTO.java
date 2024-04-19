package org.example.project.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private  Long id;
    private  String accountNumber;
    private  BigDecimal balance;
    private  UserDTO userDto;
}
