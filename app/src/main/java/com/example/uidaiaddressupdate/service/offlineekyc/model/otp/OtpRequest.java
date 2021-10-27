package com.example.uidaiaddressupdate.service.offlineekyc.model.otp;

public class OtpRequest {
    private String uidNumber;
    private String captchaTxnId;
    private String captchaValue;
    private String transactionId;

    public String getUidNumber() {
        return uidNumber;
    }

    public void setUidNumber(String uidNumber) {
        this.uidNumber = uidNumber;
    }

    public String getCaptchaTxnId() {
        return captchaTxnId;
    }

    public void setCaptchaTxnId(String captchaTxnId) {
        this.captchaTxnId = captchaTxnId;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
