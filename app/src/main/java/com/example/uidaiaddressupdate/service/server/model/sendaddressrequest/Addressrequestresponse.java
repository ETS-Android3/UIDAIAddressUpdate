package com.example.uidaiaddressupdate.service.server.model.sendaddressrequest;

import java.io.Serializable;

public class Addressrequestresponse implements Serializable {
    private String body;
    private String transactionNo;

    public Addressrequestresponse(String body, String transactionNo) {
        this.body = body;
        this.transactionNo = transactionNo;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }
}
