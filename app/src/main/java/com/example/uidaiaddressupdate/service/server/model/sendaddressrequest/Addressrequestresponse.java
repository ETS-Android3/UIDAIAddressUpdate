package com.example.uidaiaddressupdate.service.server.model.sendaddressrequest;

import java.io.Serializable;

public class Addressrequestresponse implements Serializable {
    private String body;
    private String transactionID;

    public Addressrequestresponse(String body, String transactionID) {
        this.body = body;
        this.transactionID = transactionID;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionID() {
        return transactionID;
    }
}
