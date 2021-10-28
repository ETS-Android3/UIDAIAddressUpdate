package com.example.uidaiaddressupdate.service.auth.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Authotprequest {
    @SerializedName("transactionNo")
    private String transactionNo;

    @SerializedName("otp")
    private String otp;

    @SerializedName("deviceID")
    private String deviceID;

    public Authotprequest(String transactionNo, String otp, String deviceID, String publicKey) {
        this.transactionNo = transactionNo;
        this.otp = otp;
        this.deviceID = deviceID;
        this.publicKey = publicKey;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
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

    @SerializedName("publicKey")
    private String publicKey;
}
