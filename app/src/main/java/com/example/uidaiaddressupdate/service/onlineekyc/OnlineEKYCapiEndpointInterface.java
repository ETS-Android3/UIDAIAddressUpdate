package com.example.uidaiaddressupdate.service.onlineekyc;

import com.example.uidaiaddressupdate.service.onlineekyc.model.otp.OtpRequest;
import com.example.uidaiaddressupdate.service.onlineekyc.model.otp.OtpResponse;
import com.example.uidaiaddressupdate.service.onlineekyc.model.ekyconline.OnlineEKYCRequest;
import com.example.uidaiaddressupdate.service.onlineekyc.model.ekyconline.OnlineEKYCResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OnlineEKYCapiEndpointInterface {
    @Headers({"Content-Type:application/json"})
    @POST("onlineekyc/getOtp/")
    Call<OtpResponse> sendOtpOnPhone(@Body OtpRequest otpRequest);

    @Headers({"Content-Type:application/json"})
    @POST("onlineekyc/getEkyc/")
    Call<OnlineEKYCResponse> getOnlineEKYC(@Body OnlineEKYCRequest onlineEKYCRequest);
}
