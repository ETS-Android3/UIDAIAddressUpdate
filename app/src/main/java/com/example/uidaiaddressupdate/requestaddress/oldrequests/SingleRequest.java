package com.example.uidaiaddressupdate.requestaddress.oldrequests;

public class SingleRequest {
    private String sharecode;
    private Integer status;
    private String transactionId;

    public SingleRequest(String sharecode, Integer status, String transactionId) {
        this.sharecode = sharecode;
        this.status = status;
        this.transactionId = transactionId;
    }

    public String getSharecode() {
        return sharecode;
    }

    public void setSharecode(String sharecode) {
        this.sharecode = sharecode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
