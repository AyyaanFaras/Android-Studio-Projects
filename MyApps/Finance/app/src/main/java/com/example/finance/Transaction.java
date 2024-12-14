package com.example.finance;

public class Transaction {
    public int id;
    public String description;
    public String amount;

    public Transaction(int id, String description, String amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }
}

