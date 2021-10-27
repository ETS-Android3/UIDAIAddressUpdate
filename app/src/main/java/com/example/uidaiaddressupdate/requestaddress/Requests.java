package com.example.uidaiaddressupdate.requestaddress;

public class Requests {
    private String name;
    private String phone;
    private String picture;
    private String transactionId;
    private Integer status;

    public Requests(String name, String phone, String picture, String transactionId, Integer status) {
        this.name = name;
        this.phone = phone;
        this.picture = picture;
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
