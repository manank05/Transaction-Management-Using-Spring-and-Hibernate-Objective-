package com.example.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int fromAccount;
    private int toAccount;
    private double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public TransactionRecord() {}

    public TransactionRecord(int fromAccount, int toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.timestamp = new Date();
    }

    // Getters and Setters
}
