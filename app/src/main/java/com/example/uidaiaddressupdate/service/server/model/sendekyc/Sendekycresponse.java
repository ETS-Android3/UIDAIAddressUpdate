package com.example.uidaiaddressupdate.service.server.model.sendekyc;

import java.io.Serializable;

public class Sendekycresponse implements Serializable {
    private String message;
    private String transactionNo;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
}
