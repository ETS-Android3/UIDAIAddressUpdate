package com.example.uidaiaddressupdate.requestaddress;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.EncryptionUtils;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.SharedPrefHelper;
import com.example.uidaiaddressupdate.XMLUtils;
import com.example.uidaiaddressupdate.database.RenterTransactions;
import com.example.uidaiaddressupdate.database.RenterTransactionsDao;
import com.example.uidaiaddressupdate.database.TransactionDatabase;
import com.example.uidaiaddressupdate.service.server.ServerApiService;
import com.example.uidaiaddressupdate.service.server.model.getekyc.GetEkycRequest;
import com.example.uidaiaddressupdate.service.server.model.getekyc.GetEkycResponse;
import com.google.gson.Gson;

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
        status.setText(renterTransactions.getTransactionStatus() + " : " + getMessage(renterTransactions.getTransactionStatus()));

        withdraw_btn = (AppCompatButton) view.findViewById(R.id.request_detail_withdraw);
        edit_address_btn = (AppCompatButton) view.findViewById(R.id.request_edit_and_update_address);


        if(renterTransactions.getTransactionStatus().equals(Constants.STATUS_ACCEPTED)){
            edit_address_btn.setVisibility(View.VISIBLE);
        }else{
            edit_address_btn.setVisibility(View.GONE);
        }

//        if(renterTransactions.getTransactionStatus().equals(Constants.STATUS_COMMITED) || renterTransactions.getTransactionStatus().equals(Constants.STATUS_ABORTED)){
//            withdraw_btn.setVisibility(View.GONE);
//        }else{
//            withdraw_btn.setVisibility(View.VISIBLE);
//        }
        withdraw_btn.setVisibility(View.GONE);

        edit_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Getting Address of Lender...");
                progressDialog.show();
                GetEkycRequest getEkycRequest = new GetEkycRequest(SharedPrefHelper.getUidToken(getContext()),SharedPrefHelper.getAuthToken(getContext()),transactionID);
                ServerApiService.getEkyc(getEkycRequest).enqueue(new Callback<GetEkycResponse>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(Call<GetEkycResponse> call, Response<GetEkycResponse> response) {
                        switch (response.code()){
                            case 200:
                                AddressModel addressModel = null;
                                Toast.makeText(getContext(), "Got EKyc", Toast.LENGTH_SHORT).show();
                                Log.d("eKYC",response.body().getEncryptedEKYC());
                                try {
                                    String decryptedPasscode = EncryptionUtils.decryptMessage(response.body().getEncryptedPasscode());
                                    String eKYCxml = XMLUtils.getKYCxmlFromZip(response.body().getEncryptedEKYC(),decryptedPasscode);
                                    addressModel = XMLUtils.getAddressFromEKYCxml(eKYCxml);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    progressDialog.dismiss();
                                    return ;
                                }
                                progressDialog.dismiss();
                                Bundle bundle = new Bundle();
                                bundle.putString("addressModel",new Gson().toJson(addressModel));
                                bundle.putString(Constants.KEY_TRANSACTION_ID,transactionID);
                                Navigation.findNavController(view).navigate(R.id.action_requestDetails_to_editAddress,bundle);

                                break;

                            case 400:
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(),"Invalid request parameters",Toast.LENGTH_SHORT).show();
                                break;

                            case 403:
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(),"Invalid transactionID. Unable to fetch eKYC",Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(),"Error code: "+String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }

                    @Override
                    public void onFailure(Call<GetEkycResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),"Unable to contact the server. Try again later",Toast.LENGTH_SHORT).show();
                        // Error
                    }
                });
            }
        });

        return view;
    }

    private String getMessage(String status){
        if(status.equals(Constants.STATUS_ABORTED)){
            return "You request has been aborted due to some reason.";
        }else if (status.equals(Constants.STATUS_ACCEPTED)){
            return "Lender has shared his/her address with you.";
        }else if (status.equals(Constants.STATUS_INIT)){
            return "You have initiated the request.";
        }else if (status.equals(Constants.STATUS_REJECTED)){
            return "Sorry you request has been rejected";
        }else if (status.equals(Constants.STATUS_COMMITED)){
            return "Congrats, your request has been successfully updated";
        }else if (status.equals(Constants.STATUS_SHARED)){
            return "Lenders Ekyc has been shared with you";
        }else {
            return "Unkown Status";
        }
    }
}