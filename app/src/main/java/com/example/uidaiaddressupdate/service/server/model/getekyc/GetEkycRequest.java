package com.example.uidaiaddressupdate.service.server.model.getekyc;

import com.google.gson.annotations.SerializedName;

public class GetEkycRequest {
    @SerializedName("txnID")
    private String transactionID;

    public GetEkycRequest(String transactionID) {
        this.transactionID = transactionID;
    }
}
