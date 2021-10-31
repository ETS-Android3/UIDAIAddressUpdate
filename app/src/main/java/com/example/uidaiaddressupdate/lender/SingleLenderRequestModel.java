package com.example.uidaiaddressupdate.lender;

public class SingleLenderRequestModel {
    private String username;
    private String sharecode;
    private String phonenumber;
    private String image;
    private String transactionId;

    public SingleLenderRequestModel(String username, String sharecode, String phonenumber, String image, String transactionId) {
        this.username = username;
        this.sharecode = sharecode;
        this.phonenumber = phonenumber;
        this.image = image;
        this.transactionId = transactionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSharecode() {
        return sharecode;
    }

    public void setSharecode(String sharecode) {
        this.sharecode = sharecode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
