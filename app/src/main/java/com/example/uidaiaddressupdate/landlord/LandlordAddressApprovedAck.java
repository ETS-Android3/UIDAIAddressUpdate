package com.example.uidaiaddressupdate.landlord;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.database.LandlordTransactionsDao;
import com.example.uidaiaddressupdate.database.TransactionDatabase;


public class LandlordAddressApprovedAck extends Fragment {

    private AppCompatButton address_approved_ack_continue_btn;
    private TransactionDatabase db;
    private LandlordTransactionsDao landlordTransactionsDao;


    public LandlordAddressApprovedAck() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String transactionId = getArguments().getString(Constants.KEY_TRANSACTION_ID);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_landlord_address_approved_ack, container, false);
        db = TransactionDatabase.getInstance(getContext());
        landlordTransactionsDao = db.landlordTransactionsDao();
        address_approved_ack_continue_btn = (AppCompatButton) view.findViewById(R.id.address_approved_ack_continue_btn);
        address_approved_ack_continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                landlordTransactionsDao.deleteTransaction(transactionId);
                getActivity().finish();
            }
        });
        return view;
    }
}