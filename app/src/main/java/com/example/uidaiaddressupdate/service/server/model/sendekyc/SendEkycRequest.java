package com.example.uidaiaddressupdate.service.server.model.sendekyc;

import com.google.gson.annotations.SerializedName;

public class SendEkycRequest {
    @SerializedName("uidToken")
    private String uidToken;

    @SerializedName("authToken")
    private String authToken;

    @SerializedName("transactionID")
    private String transactionID;

    @SerializedName("txnNumber")
    private String txnNumber;

    @SerializedName("otp")
    private String otp;

    @SerializedName("uid")
    private String uid;

    @SerializedName("epss")
    private String epss;

    @SerializedName("pss")
    private String pss;

    public SendEkycRequest(String uidToken, String authToken, String transactionID, String txnNumber, String otp, String uid, String epss, String pss) {
        this.uidToken = uidToken;
        this.authToken = authToken;
        this.transactionID = transactionID;
        this.txnNumber = txnNumber;
        this.otp = otp;
        this.uid = uid;
        this.epss = epss;
        this.pss = pss;
    }

    public String getUidToken() {
        return uidToken;
    }

    public void setUidToken(String uidToken) {
        this.uidToken = uidToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTxnNumber() {
        return txnNumber;
    }

    public void setTxnNumber(String txnNumber) {
        this.txnNumber = txnNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
