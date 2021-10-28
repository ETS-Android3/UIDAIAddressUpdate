package com.example.uidaiaddressupdate.landlord;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uidaiaddressupdate.R;

public class LandlordSingleRequests extends Fragment {

    private ImageView captchaImage;
    private TextView captchaRefresh;
    private EditText captchaEditText;
    private AppCompatButton captchaContinue;
    private View view;


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

        captchaContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send to OTP page
            }
        });
        return view;
    }

    private void SendToOTPPage( String transactionID){
        Navigation.findNavController(view).navigate(R.id.action_landlordSingleRequests_to_landlordOtpPage);
    }
}