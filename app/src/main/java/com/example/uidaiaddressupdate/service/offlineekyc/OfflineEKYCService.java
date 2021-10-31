package com.example.uidaiaddressupdate.service.offlineekyc;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.service.offlineekyc.model.captcha.CaptchaRequest;
import com.example.uidaiaddressupdate.service.offlineekyc.model.captcha.CaptchaResponse;
import com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline.OfflineEkycXMLRequest;
import com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline.OfflineEkycXMLResponse;
import com.example.uidaiaddressupdate.service.offlineekyc.model.otp.OtpRequest;
import com.example.uidaiaddressupdate.service.offlineekyc.model.otp.OtpResponse;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfflineEKYCService {
    private static EKYCapiEndpointInterface apiEndpointInterface;
    private static Retrofit retrofit = null;

    public static EKYCapiEndpointInterface getApiInstance(){
        if(apiEndpointInterface == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.UIDAI_SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiEndpointInterface = retrofit.create(EKYCapiEndpointInterface.class);
        }
        return apiEndpointInterface;
    }

    public static Call<CaptchaResponse> makeCaptchaCall(){
        CaptchaRequest captchaRequest = new CaptchaRequest();
        captchaRequest.setCaptchaLength(ServiceConstants.CAPTCHA_LENGTH);
        captchaRequest.setCaptchaType(ServiceConstants.CAPTCHA_TYPE);
        captchaRequest.setLangCode(ServiceConstants.CAPTCHA_LANG_CODE);

        return getApiInstance().fetchCaptchaResponse(captchaRequest);

    }

    public static Call<OtpResponse> makeOTPCall(String UidToken, String captchaTxnId, String captchaText){
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setUidNumber(UidToken);
        otpRequest.setCaptchaTxnId(captchaTxnId);
        otpRequest.setCaptchaValue(captchaText);
        otpRequest.setTransactionId("mAadhaar:"+UUID.randomUUID().toString());

        return getApiInstance().sendOtpOnPhone(UUID.randomUUID().toString(),otpRequest);

    }

    public static Call<OfflineEkycXMLResponse> makeOfflineEKYCCall(String UidToken, String otp, String otpTxnId,String shareCode) {
        OfflineEkycXMLRequest offlineEkycXMLRequest = new OfflineEkycXMLRequest();
        offlineEkycXMLRequest.setUid(UidToken);
        offlineEkycXMLRequest.setShareCode(shareCode);
        offlineEkycXMLRequest.setOtp(otp);
        offlineEkycXMLRequest.setTxnNumber(otpTxnId);

        return getApiInstance().fetchEKYC(offlineEkycXMLRequest);
    }
}
