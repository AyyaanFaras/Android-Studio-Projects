package com.example.finance;

public class TransactionModel12 {
    private int id;
    private int icon;
    private String type;
    private String date;
    private String amount;

    public TransactionModel12(int id, int icon, String type, String date, String amount) {
        this.id = id;
        this.icon = icon;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }
}
