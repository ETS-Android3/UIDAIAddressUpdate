package com.example.uidaiaddressupdate.service.server.model.rejectrequest;

import java.io.Serializable;

public class RejectRequestResponse implements Serializable {
    private String transactionID;
    private String body;

    public RejectRequestResponse(String transactionID, String body) {
        this.transactionID = transactionID;
        this.body = body;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
