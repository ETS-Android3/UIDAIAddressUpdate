package com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline;

public class AadhaarOrVidNumberParam {
    private long aadhaarOrVidNumber;
    private String txnNumber;
    private String shareCode;
    private String otp;
    private String deviceId;
    private String transactionId;
    private String unifiedAppRequestTxnId;
    private String uid;
    private String vid;

    public long getAadhaarOrVidNumber() {
        return aadhaarOrVidNumber;
    }

    public void setAadhaarOrVidNumber(long aadhaarOrVidNumber) {
        this.aadhaarOrVidNumber = aadhaarOrVidNumber;
    }

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUnifiedAppRequestTxnId() {
        return unifiedAppRequestTxnId;
    }

    public void setUnifiedAppRequestTxnId(String unifiedAppRequestTxnId) {
        this.unifiedAppRequestTxnId = unifiedAppRequestTxnId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
