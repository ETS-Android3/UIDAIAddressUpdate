package com.example.uidaiaddressupdate.service.server.model.sendekyc;

import com.google.gson.annotations.SerializedName;

public class Sendekycrequest {
    @SerializedName("uidToken")
    private String uidToken;

    @SerializedName("authToken")
    private String authToken;

    @SerializedName("transactionID")
    private String transactionID;

    @SerializedName("eKYC")
    private String eKYC;

    @SerializedName("filename")
    private String filename;

    @SerializedName("passcode")
    private String passcode;

    public Sendekycrequest(String uidToken, String authToken, String transactionID, String eKYC, String filename, String passcode) {
        this.uidToken = uidToken;
        this.authToken = authToken;
        this.transactionID = transactionID;
        this.eKYC = eKYC;
        this.filename = filename;
        this.passcode = passcode;
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

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void seteKYC(String eKYC) {
        this.eKYC = eKYC;
    }

    public String geteKYC() {
        return eKYC;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getPasscode() {
        return passcode;
    }
}
