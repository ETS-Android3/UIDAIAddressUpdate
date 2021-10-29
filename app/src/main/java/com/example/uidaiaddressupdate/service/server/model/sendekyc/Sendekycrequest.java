package com.example.uidaiaddressupdate.service.server.model.sendekyc;

import com.google.gson.annotations.SerializedName;

public class Sendekycrequest {
    @SerializedName("txnID")
    private String txnID;

    @SerializedName("eKYC")
    private String eKYC;

    @SerializedName("filename")
    private String filename;

    @SerializedName("passcode")
    private String passcode;

    public Sendekycrequest(String txnID, String eKYC, String filename, String passcode) {
        this.txnID = txnID;
        this.eKYC = eKYC;
        this.filename = filename;
        this.passcode = passcode;
    }

    public void setTxnID(String txnID) {
        this.txnID = txnID;
    }

    public String getTxnID() {
        return txnID;
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
