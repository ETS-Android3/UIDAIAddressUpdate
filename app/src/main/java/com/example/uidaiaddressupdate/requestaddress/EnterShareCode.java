package com.example.uidaiaddressupdate.requestaddress;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uidaiaddressupdate.R;

public class EnterShareCode extends Fragment {

    private Button share_code_submit;
    public EnterShareCode() {
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
        View view = inflater.inflate(R.layout.fragment_enter_share_code, container, false);
        share_code_submit = (Button) view.findViewById(R.id.share_code_submit);
        share_code_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_enterShareCode_to_renterOTP);
            }
        });
        return view;
    }
}