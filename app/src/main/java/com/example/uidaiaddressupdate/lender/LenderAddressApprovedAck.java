package com.example.uidaiaddressupdate.lender;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.database.LenderTransactionsDao;
import com.example.uidaiaddressupdate.database.TransactionDatabase;


public class LenderAddressApprovedAck extends Fragment {

    private AppCompatButton address_approved_ack_continue_btn;
    private TransactionDatabase db;
    private LenderTransactionsDao lenderTransactionsDao;


    public LenderAddressApprovedAck() {
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
        View view = inflater.inflate(R.layout.fragment_lender_address_approved_ack, container, false);
        db = TransactionDatabase.getInstance(getContext());
        lenderTransactionsDao = db.lenderTransactionsDao();
        address_approved_ack_continue_btn = (AppCompatButton) view.findViewById(R.id.address_approved_ack_continue_btn);
        address_approved_ack_continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lenderTransactionsDao.deleteTransaction(transactionId);
                getActivity().finish();
            }
        });
        return view;
    }
}