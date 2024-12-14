package com.example.finance;

public class UpComingModel {
    String reason,date;
    int img;


    public UpComingModel(String reason, String date, int img) {
        this.reason = reason;
        this.date = date;
        this.img = img;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
