package com.example.uidaiaddressupdate.landlord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.uidaiaddressupdate.R;


public class LandlordOtpPage extends Fragment {

    private EditText otp_edit_text;
    private TextView resend_otp;
    private Button submit_otp;

    public LandlordOtpPage() {
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
        View view =  inflater.inflate(R.layout.fragment_landlord_otp_page, container, false);

        otp_edit_text = (EditText) view.findViewById(R.id.landlord_otp_et_enter_otp);
        resend_otp = (TextView) view.findViewById(R.id.landlord_otp_resend_otp);
        submit_otp = (Button) view.findViewById(R.id.landlord_otp_verify_button);
        return view;
    }
}