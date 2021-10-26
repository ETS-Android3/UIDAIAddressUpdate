package com.example.uidaiaddressupdate.service.offlineekyc;

import com.example.uidaiaddressupdate.service.offlineekyc.model.captcha.CaptchaRequest;
import com.example.uidaiaddressupdate.service.offlineekyc.model.captcha.CaptchaResponse;
import com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline.AadhaarOrVidNumberParam;
import com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline.OfflineEkycXMLResponse;
import com.example.uidaiaddressupdate.service.offlineekyc.model.otp.OtpRequest;
import com.example.uidaiaddressupdate.service.offlineekyc.model.otp.OtpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EKYCapiEndpointInterface {

    @Headers({"Content-Type: application/json"})
    @POST("https://stage1.uidai.gov.in/unifiedAppAuthService/api/v2/get/captcha")
    Call<CaptchaResponse> fetchCaptchaResponse(@Body CaptchaRequest captchaRequest);

    @Headers({"appid: appID" ,
            "Accept-Language:en_in",
            "Content-Type: application/json"})
    @POST("https://stage1.uidai.gov.in/unifiedAppAuthService/api/v2/generate/aadhaar/otp")
    Call<OtpResponse> sendOtpOnPhone(@Header("x-request-id") String requestId, @Body OtpRequest otpRequest);

    @Headers({"Content-Type: application/json"})
    @POST("https://stage1.uidai.gov.in/eAadhaarService/api/downloadOfflineEkyc")
    Call<OfflineEkycXMLResponse> fetchEKYC(@Body AadhaarOrVidNumberParam eKYCRequest);

}
