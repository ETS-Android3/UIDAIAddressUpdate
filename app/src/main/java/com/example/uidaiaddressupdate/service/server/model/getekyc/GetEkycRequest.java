package com.example.uidaiaddressupdate.service.server.model.getekyc;

import com.google.gson.annotations.SerializedName;

public class GetEkycRequest {
    @SerializedName("uidToken")
    private String uidToken;

    @SerializedName("authToken")
    private String authToken;

    @SerializedName("transactionID")
    private String transactionID;

    public GetEkycRequest(String uidToken, String authToken, String transactionID) {
        this.uidToken = uidToken;
        this.authToken = authToken;
        this.transactionID = transactionID;
    }
}
