package com.example.uidaiaddressupdate.service.auth.model;

import java.io.Serializable;

public class Authuidresponse implements Serializable {
    private String transactionID;
    private String message;

    public String getTransactionID() {
        return transactionID;
    }

    public String getMessage() {
        return message;
    }

    public void setTransactionID(String transactionID, String message) {
        this.transactionID = transactionID;
        this.message = message;
    }
}
