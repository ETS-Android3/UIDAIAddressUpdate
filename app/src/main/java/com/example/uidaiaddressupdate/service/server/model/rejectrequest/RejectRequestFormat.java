package com.example.uidaiaddressupdate.service.server.model.rejectrequest;

import com.google.gson.annotations.SerializedName;

public class RejectRequestFormat {
    @SerializedName("transactionID")
    private String transactionID;

    @SerializedName("uidToken")
    private String uidToken;

    public RejectRequestFormat(String transactionID, String uidToken) {
        this.transactionID = transactionID;
        this.uidToken = uidToken;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setUidToken(String uidToken) {
        this.uidToken = uidToken;
    }

    public String getUidToken() {
        return uidToken;
    }
}
