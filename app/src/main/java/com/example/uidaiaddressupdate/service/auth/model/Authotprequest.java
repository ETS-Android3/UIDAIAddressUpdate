package com.example.uidaiaddressupdate.service.auth.model;

public class Authotprequest {
    private String transactionNo;
    private String otp;
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

    private String publicKey;
}
