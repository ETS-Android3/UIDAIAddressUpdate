package com.example.uidaiaddressupdate.service.offlineekyc;

import com.example.uidaiaddressupdate.service.offlineekyc.constant.Constants;
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

    public static Call<CaptchaResponse> makeCaptchaCall(){
        CaptchaRequest captchaRequest = new CaptchaRequest();
        captchaRequest.setCaptchaLength(Constants.CAPTCHA_LENGTH);
        captchaRequest.setCaptchaType(Constants.CAPTCHA_TYPE);
        captchaRequest.setLangCode(Constants.CAPTCHA_LANG_CODE);

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
