package com.example.uidaiaddressupdate.service.onlineekyc.model.otp;

import com.google.gson.annotations.SerializedName;

public class OtpRequest {
    @SerializedName("uid")
    private String uid;

    @SerializedName("txnId")
    private String txnId;

    public OtpRequest(String uid, String txnId) {
        this.uid = uid;
        this.txnId = txnId;
    }
}
