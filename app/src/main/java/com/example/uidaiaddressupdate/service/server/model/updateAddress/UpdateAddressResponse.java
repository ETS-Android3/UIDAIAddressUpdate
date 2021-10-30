package com.example.uidaiaddressupdate.service.server.model.updateAddress;

public class UpdateAddressResponse {
    private String body;
    private String transactionID;
    private String status;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
