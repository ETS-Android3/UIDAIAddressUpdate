package com.example.uidaiaddressupdate.service.onlineekyc.model.otp;

import com.google.gson.annotations.SerializedName;

public class OtpResponse {
    private String status;
    private String errCode;

    public String getStatus() {
        return status;
    }

    public String getErrCode() {
        return errCode;
    }
}
