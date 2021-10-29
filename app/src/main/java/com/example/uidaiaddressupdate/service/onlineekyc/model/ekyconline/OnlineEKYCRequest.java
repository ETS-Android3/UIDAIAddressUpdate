package com.example.uidaiaddressupdate.service.onlineekyc.model.ekyconline;

import com.google.gson.annotations.SerializedName;

public class OnlineEKYCRequest {
    @SerializedName("uid")
    private String uid;

    @SerializedName("txnId")
    private String txnId;

    @SerializedName("otp")
    private String otp;

    public OnlineEKYCRequest(String uid, String txnId, String otp) {
        this.uid = uid;
        this.txnId = txnId;
        this.otp = otp;
    }
}
