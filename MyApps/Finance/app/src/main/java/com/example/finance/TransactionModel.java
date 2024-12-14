package com.example.finance;

public class TransactionModel {
    private String date;
    private String rupees;
    private String reason;
    private int image;

    public TransactionModel(int image,String reason, String date,  String rupees) {
        this.image = image;
        this.reason = reason;
        this.date = date;
        this.rupees = rupees;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRupees() {
        return rupees;
    }

    public void setRupees(String rupees) {
        this.rupees = rupees;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
