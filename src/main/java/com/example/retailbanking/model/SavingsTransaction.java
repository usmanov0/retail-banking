package com.example.retailbanking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingsTransaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private Double amount;
    private BigDecimal availableBalance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public SavingsTransaction(Date date, String depositToSavingsAccount, String account, String finished, double amount, BigDecimal accountBalance, SavingsAccount savingsAccount) {
    }
}
