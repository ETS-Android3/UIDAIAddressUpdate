package com.example.uidaiaddressupdate.service.offlineekyc.model.captcha;

public class CaptchaResponse {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaptchaBase64String() {
        return captchaBase64String;
    }

    public void setCaptchaBase64String(String captchaBase64String) {
        this.captchaBase64String = captchaBase64String;
    }

    public String getCaptchaTxnId() {
        return captchaTxnId;
    }

    public void setCaptchaTxnId(String captchaTxnId) {
        this.captchaTxnId = captchaTxnId;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String captchaBase64String;
    private String captchaTxnId;
    private String requestedDate;
    private int statusCode;
    private String message;
}
