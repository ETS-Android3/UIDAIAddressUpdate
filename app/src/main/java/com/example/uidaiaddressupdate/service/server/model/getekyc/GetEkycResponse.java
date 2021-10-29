package com.example.uidaiaddressupdate.service.server.model.getekyc;

import java.io.Serializable;

public class GetEkycResponse implements Serializable {
    private String encryptedEKYC;
    private String encryptedPasscode;
    private String filename;
    private String txnID;

    public GetEkycResponse(String encryptedEKYC, String encryptedPasscode, String filename, String txnID) {
        this.encryptedEKYC = encryptedEKYC;
        this.encryptedPasscode = encryptedPasscode;
        this.filename = filename;
        this.txnID = txnID;
    }

    public String getEncryptedEKYC() {
        return encryptedEKYC;
    }

    public void setEncryptedEKYC(String encryptedEKYC) {
        this.encryptedEKYC = encryptedEKYC;
    }

    public String getEncryptedPasscode() {
        return encryptedPasscode;
    }

    public void setEncryptedPasscode(String encryptedPasscode) {
        this.encryptedPasscode = encryptedPasscode;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTxnID() {
        return txnID;
    }

    public void setTxnID(String txnID) {
        this.txnID = txnID;
    }
}
