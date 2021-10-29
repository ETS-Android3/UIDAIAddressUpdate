package com.example.uidaiaddressupdate.service.auth.model;

import java.io.Serializable;

public class Authuidresponse implements Serializable {
    private String transactionNo;

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
}
