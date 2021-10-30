package com.example.uidaiaddressupdate.service.auth.model;

import com.google.gson.annotations.SerializedName;

public class Authotprequest {
    @SerializedName("transactionID")
    private String transactionID;

    @SerializedName("otp")
    private String otp;

    @SerializedName("deviceID")
    private String deviceID;

    @SerializedName("publicKey")
    private String publicKey;

    @SerializedName("uid")
    private String uid;

    public Authotprequest(String transactionID, String otp, String deviceID, String publicKey, String uid) {
        this.transactionID = transactionID;
        this.otp = otp;
        this.deviceID = deviceID;
        this.publicKey = publicKey;
        this.uid = uid;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getUid() { return uid;  }

    public void setUid(String uid) { this.uid = uid; }
}
