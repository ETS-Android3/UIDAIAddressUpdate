package com.example.uidaiaddressupdate.requestaddress;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uidaiaddressupdate.R;

public class RequestDetails extends Fragment {

    private AppCompatButton withdraw_btn, edit_address_btn;
    public RequestDetails() {
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
        View  view =  inflater.inflate(R.layout.fragment_request_details, container, false);
        withdraw_btn = (AppCompatButton) view.findViewById(R.id.request_detail_withdraw);
        edit_address_btn = (AppCompatButton) view.findViewById(R.id.request_edit_and_update_address);

        edit_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_requestDetails_to_editAddress);
            }
        });

        return view;
    }
}