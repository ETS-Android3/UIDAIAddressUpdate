package com.example.uidaiaddressupdate.service.offlineekyc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.uidaiaddressupdate.service.auth.AuthApiEndpointInterface;
import com.example.uidaiaddressupdate.service.offlineekyc.constant.Constants;
import com.example.uidaiaddressupdate.service.offlineekyc.model.captcha.CaptchaRequest;
import com.example.uidaiaddressupdate.service.offlineekyc.model.captcha.CaptchaResponse;
import com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline.AadhaarOrVidNumberParam;
import com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline.OfflineEkycXMLResponse;
import com.example.uidaiaddressupdate.service.offlineekyc.model.otp.OtpRequest;
import com.example.uidaiaddressupdate.service.offlineekyc.model.otp.OtpResponse;

import java.util.UUID;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OfflineEKYCService {
    private static EKYCapiEndpointInterface apiEndpointInterface;
    private static Retrofit retrofit = null;

    public static EKYCapiEndpointInterface getApiInstance(){
        if(apiEndpointInterface == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://stage1.uidai.gov.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiEndpointInterface = retrofit.create(EKYCapiEndpointInterface.class);
        }
        return apiEndpointInterface;
    }

//    public String getOfflineEKYC() throws Exception {
//        UidToken = "";
//        Retrofit retrofit = new Retrofit.Builder()
//                .build();
//
//        apiservice = retrofit.create(EKYCapiEndpointInterface.class);
//
//        CaptchaResponse captchaResponse = makeCaptchaCall();
//
//        byte[] base64Val = Base64.decode(captchaResponse.getCaptchaBase64String(),Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(base64Val,0,base64Val.length);
//
//        OtpResponse otpResponse = makeOTPCall(captchaResponse.getCaptchaTxnId(),"");
//
//        OfflineEkycXMLResponse offlineEkycXMLResponse = makeOfflineEKYCCall("",otpResponse.getTxnId());
//        return offlineEkycXMLResponse.geteKycXML();
//    }

    public static CaptchaResponse makeCaptchaCall() throws Exception {
        CaptchaRequest captchaRequest = new CaptchaRequest();
        captchaRequest.setCaptchaLength(Constants.CAPTCHA_LENGTH);
        captchaRequest.setCaptchaType(Constants.CAPTCHA_TYPE);
        captchaRequest.setLangCode(Constants.CAPTCHA_LANG_CODE);

        Response<CaptchaResponse> response = getApiInstance().fetchCaptchaResponse(captchaRequest).execute();
        if(response.isSuccessful()) {
            return response.body();
        }
        else {
            throw new Exception("Did not get proper response from Captcha server");
        }
    }

    private OtpResponse makeOTPCall(String UidToken, String captchaTxnId, String captchaText) throws Exception {
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setUidNumber(UidToken);
        otpRequest.setCaptchaTxnId(captchaTxnId);
        otpRequest.setCaptchaValue(captchaText);
        otpRequest.setTransactionId(UUID.randomUUID().toString());

        Response<OtpResponse> response = getApiInstance().sendOtpOnPhone(UUID.randomUUID().toString(),otpRequest).execute();

        if(response.isSuccessful()) {
            return response.body();
        }
        else {
            throw new Exception("Did not get proper response from OTP server");
        }
    }
//
//    private OfflineEkycXMLResponse makeOfflineEKYCCall(String otp, String otpTxnId) throws Exception {
//        AadhaarOrVidNumberParam aadhaarOrVidNumberParam = new AadhaarOrVidNumberParam();
//        aadhaarOrVidNumberParam.setUid(UidToken);
//        aadhaarOrVidNumberParam.setShareCode(Constants.EKYC_SHARE_CODE);
//        aadhaarOrVidNumberParam.setOtp(otp);
//        aadhaarOrVidNumberParam.setTxnNumber(otpTxnId);
//
//        Response<OfflineEkycXMLResponse> response = apiservice.fetchEKYC(aadhaarOrVidNumberParam).execute();
//
//        if(response.isSuccessful()) {
//            return response.body();
//        }
//        else {
//            throw new Exception("Did not get proper response from offline eKYC server");
//        }
//    }
}
