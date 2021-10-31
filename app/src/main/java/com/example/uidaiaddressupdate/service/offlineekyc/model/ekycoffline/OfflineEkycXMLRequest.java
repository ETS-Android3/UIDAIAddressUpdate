package com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline;

public class OfflineEkycXMLRequest {
    private String txnNumber;
    private String shareCode;
    private String otp;
    private String uid;

    public String getTxnNumber() {
        return txnNumber;
    }

    public void setTxnNumber(String txnNumber) {
        this.txnNumber = txnNumber;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
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
