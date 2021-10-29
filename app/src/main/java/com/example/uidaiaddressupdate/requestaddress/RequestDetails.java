package com.example.uidaiaddressupdate.requestaddress;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.database.RenterTransactions;
import com.example.uidaiaddressupdate.database.RenterTransactionsDao;
import com.example.uidaiaddressupdate.database.TransactionDatabase;
import com.example.uidaiaddressupdate.service.server.ServerApiService;
import com.example.uidaiaddressupdate.service.server.model.getekyc.GetEkycResponse;

public class RequestDetails extends Fragment {

    private AppCompatButton withdraw_btn, edit_address_btn;
    private TransactionDatabase transactionDatabase;
    private RenterTransactionsDao renterTransactionsDao;
    private TextView shareCode, status;
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
        String transactionID = getArguments().getString(Constants.KEY_TRANSACTION_ID);

        transactionDatabase = TransactionDatabase.getInstance(getContext());
        renterTransactionsDao = transactionDatabase.renterTransactionsDao();
        RenterTransactions renterTransactions = renterTransactionsDao.getTransaction(transactionID);

        View  view =  inflater.inflate(R.layout.fragment_request_details, container, false);

        shareCode = (TextView)view.findViewById(R.id.request_detail_share_code);
        status = (TextView) view.findViewById(R.id.request_details_status);

        shareCode.setText(renterTransactions.getShareCode());
        status.setText(renterTransactions.getTransactionStatus());

        withdraw_btn = (AppCompatButton) view.findViewById(R.id.request_detail_withdraw);
        edit_address_btn = (AppCompatButton) view.findViewById(R.id.request_edit_and_update_address);


        if(renterTransactions.getTransactionStatus() == Constants.STATUS_ACCEPTED){
            edit_address_btn.setVisibility(View.VISIBLE);
        }else{
            edit_address_btn.setVisibility(View.GONE);
        }

        if(renterTransactions.getTransactionStatus().equals(Constants.STATUS_COMMITED) || renterTransactions.getTransactionStatus().equals(Constants.STATUS_ABORTED)){
            withdraw_btn.setVisibility(View.GONE);
        }else{
            withdraw_btn.setVisibility(View.VISIBLE);
        }

        edit_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerApiService.getEkyc(transactionID).enqueue(new Callback<GetEkycResponse>() {
                    @Override
                    public void onResponse(Call<GetEkycResponse> call, Response<GetEkycResponse> response) {
                        //
                        Toast.makeText(getContext(), "Got EKyc", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<GetEkycResponse> call, Throwable t) {
                        //
                    }
                });
                Navigation.findNavController(view).navigate(R.id.action_requestDetails_to_editAddress);
            }
        });

        return view;
    }
}