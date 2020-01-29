package com.gestionsms.youssef.gestionsms;

public class Sms {
    private String id;
    private String date;
    private String number;
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", number='" + number + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
