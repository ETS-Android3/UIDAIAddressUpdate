package com.example.uidaiaddressupdate.service.server.model.sendekyc;

import java.io.Serializable;

public class Sendekycresponse implements Serializable {
    private String message;
    private String transactionID;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}
