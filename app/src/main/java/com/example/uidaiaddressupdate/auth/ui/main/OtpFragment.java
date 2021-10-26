package com.example.uidaiaddressupdate.auth.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.uidaiaddressupdate.MainActivity;
import com.example.uidaiaddressupdate.R;


public class OtpFragment extends Fragment {

    private EditText otp;
    private TextView resendOtp;
    private Button submit;

    public OtpFragment() {
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
        View view = inflater.inflate(R.layout.fragment_otp, container, false);
        otp = (EditText) view.findViewById(R.id.otp_et_enter_otp);
        resendOtp = (TextView) view.findViewById(R.id.otp_resend_otp);
        submit = (Button) view.findViewById(R.id.otp_verify_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendToHome();
            }
        });
        return view;
    }

    private void SendToHome(){
        getActivity().finish();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}