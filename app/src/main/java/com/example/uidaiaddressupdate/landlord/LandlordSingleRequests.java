package com.example.uidaiaddressupdate.landlord;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.service.offlineekyc.OfflineEKYCService;
import com.example.uidaiaddressupdate.service.offlineekyc.model.captcha.CaptchaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//        byte[] base64Val = Base64.decode(captchaResponse.getCaptchaBase64String(),Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(base64Val,0,base64Val.length);
//
public class LandlordSingleRequests extends Fragment {

    private ImageView captchaImage;
    private TextView captchaRefresh;
    private EditText captchaEditText;
    private AppCompatButton captchaContinue;
    private View view;
    private String captchaTxnId;

    public LandlordSingleRequests() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_landlord_single_requests, container, false);
        captchaImage = (ImageView) view.findViewById(R.id.captcha_image);
        captchaEditText = (EditText) view.findViewById(R.id.captcha_edit_text);
        captchaRefresh = (TextView) view.findViewById(R.id.captcha_refresh);
        captchaContinue = (AppCompatButton) view.findViewById(R.id.captcha_continue);


        OfflineEKYCService.makeCaptchaCall().enqueue(new Callback<CaptchaResponse>() {
            @Override
            public void onResponse(Call<CaptchaResponse> call, Response<CaptchaResponse> response) {
                CaptchaResponse captchaResponse = response.body();
                captchaTxnId = captchaResponse.getCaptchaTxnId();
                byte[] base64Val = Base64.decode(captchaResponse.getCaptchaBase64String(),Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(base64Val,0,base64Val.length);

                captchaImage.setImageBitmap(decodedByte);
            }

            @Override
            public void onFailure(Call<CaptchaResponse> call, Throwable t) {
                t.printStackTrace();
                //End App
            }
        });

        captchaContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send to OTP page
                SendToOTPPage(captchaEditText.getText().toString());
            }
        });
        return view;
    }

    private void SendToOTPPage(String captchaText){
        Bundle bundle = new Bundle();
        bundle.putString("captchaTxnId",captchaTxnId);
        bundle.putString("captchaText",captchaText);
        Navigation.findNavController(view).navigate(R.id.action_landlordSingleRequests_to_landlordOtpPage,bundle);
    }
}