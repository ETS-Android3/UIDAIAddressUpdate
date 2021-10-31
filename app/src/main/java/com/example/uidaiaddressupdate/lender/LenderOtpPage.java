package com.example.uidaiaddressupdate.lender;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.EncryptionUtils;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.SharedPrefHelper;
import com.example.uidaiaddressupdate.Util;
import com.example.uidaiaddressupdate.database.LenderTransactions;
import com.example.uidaiaddressupdate.database.TransactionDatabase;
import com.example.uidaiaddressupdate.service.offlineekyc.OfflineEKYCService;
import com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline.OfflineEkycXMLResponse;
import com.example.uidaiaddressupdate.service.offlineekyc.model.otp.OtpResponse;
import com.example.uidaiaddressupdate.service.server.ServerApiService;
import com.example.uidaiaddressupdate.service.server.model.getpublickey.PublicKeyRequest;
import com.example.uidaiaddressupdate.service.server.model.getpublickey.PublicKeyResponse;
import com.example.uidaiaddressupdate.service.server.model.sendekyc.SendEkycResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LenderOtpPage extends Fragment {

    private EditText otp_edit_text;
    private TextView resend_otp;
    private Button submit_otp;
    private String otpTxnId;
    private View view;
    private Boolean allowResendOTP = false;
    private String receiverShareCode;
    private String transactionId;

    public LenderOtpPage() {
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
        view =  inflater.inflate(R.layout.fragment_lender_otp_page, container, false);

        String captchaText = getArguments().getString("captchaText");
        String captchaTxnId = getArguments().getString("captchaTxnId");
        transactionId = getArguments().getString(Constants.KEY_TRANSACTION_ID);
        receiverShareCode = getArguments().getString(Constants.KEY_RECEIVER_SHARECODE_ID);
        sendOTP(captchaText,captchaTxnId);



        otp_edit_text = (EditText) view.findViewById(R.id.lender_otp_et_enter_otp);
        resend_otp = (TextView) view.findViewById(R.id.lender_otp_resend_otp);
        submit_otp = (Button) view.findViewById(R.id.lender_otp_verify_button);

        resend_otp.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                allowResendOTP = true;
            }
        }, 60000);

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!allowResendOTP){
                    Toast.makeText(getContext(), "wait for few seconds", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendOTP(captchaText,captchaTxnId);
                allowResendOTP = false;
                resend_otp.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        allowResendOTP = true;
                        resend_otp.setTextColor(ContextCompat.getColor(getContext(), R.color.purple_500));
                    }
                }, 60000);
            }
        });

        submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("eKYC",otp_edit_text.getText().toString());
                Log.d("eKYC",otpTxnId);
                Log.d("eKYC",transactionId);
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Verifying OTP...");

                encryptPasscodeAndSendEkyc(Util.getRandomString(),otpTxnId,otp_edit_text.getText().toString());
//                OfflineEKYCService.makeOfflineEKYCCall(SharedPrefHelper.getAadharNumber(getContext()),otp_edit_text.getText().toString(),otpTxnId,passcode).enqueue(new Callback<OfflineEkycXMLResponse>() {
//                    @RequiresApi(api = Build.VERSION_CODES.O)
//                    @Override
//                    public void onResponse(Call<OfflineEkycXMLResponse> call, Response<OfflineEkycXMLResponse> response) {
//                        progressDialog.dismiss();
//                        if (response.body().getStatus().equals("Success")){
//                            String filename = response.body().getFileName();
//                            String eKyc = response.body().geteKycXML();
//                            Log.d("eKYC", response.body().geteKycXML());
//
//                            //                        try {
//                            //                            Log.d("eKYC decrrypted", XMLUtils.getKYCxmlFromZip(response.body().geteKycXML(), passcode));
//                            //                        } catch (IOException e) {
//                            //                            e.printStackTrace();
//                            //                        }
//
//                            encryptPasscodeAndSendEkyc(filename, passcode, eKyc);
//                        }
//                        else{
//                            Toast.makeText(getActivity(),"Invalid OTP",Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<OfflineEkycXMLResponse> call, Throwable t) {
//                        Toast.makeText(getActivity(),"Unable to contact the UIDAI server. Try again later",Toast.LENGTH_SHORT).show();
//                        t.printStackTrace();
//                        progressDialog.dismiss();
//                    }
//                });
            }
        });

        return view;
    }

    private void sendToLenderAddressApprovedAckPage(){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_TRANSACTION_ID,transactionId);
        Navigation.findNavController(view).navigate(R.id.action_lenderOtpPage_to_lenderAddressApprovedAck,bundle);
    }

    private void encryptPasscodeAndSendEkyc(String passcode,String txnNumber,String otp){
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Doing background work...");
        progressDialog.show();
        ServerApiService.getApiInstance().getPublicKey(new PublicKeyRequest(SharedPrefHelper.getUidToken(getContext()),SharedPrefHelper.getAuthToken(getContext()),receiverShareCode)).enqueue(new Callback<PublicKeyResponse>() {
            @Override
            public void onResponse(Call<PublicKeyResponse> call, Response<PublicKeyResponse> response) {
                progressDialog.dismiss();
                switch (response.code()){
                    case 200:
                        String receiverPublicKey = response.body().getPublicKey();
                        Log.d("LenderOtpPage", "Public Key: "+receiverPublicKey);

                        String encryptedPasscode = null;
                        try {
                            encryptedPasscode = EncryptionUtils.encryptMessage(receiverPublicKey,passcode);
                            sendEkyc(encryptedPasscode,passcode,txnNumber,otp);
                        } catch (Exception e){
                            Toast.makeText(getActivity(),"Unable to encrypt passcode",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        break;

                    case 400:
                        Toast.makeText(getActivity(),"Invalid request parameters",Toast.LENGTH_SHORT).show();
                        break;

                    case 403:
                        Toast.makeText(getActivity(),"Invalid share code",Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(getActivity(),"Error code: "+String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<PublicKeyResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendEkyc(String epss,String pss,String txnNumber,String otp){
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Sending Address...");
        progressDialog.show();

            ServerApiService.sendEkyc(SharedPrefHelper.getUidToken(getContext()),SharedPrefHelper.getAuthToken(getContext()),transactionId,txnNumber,otp,SharedPrefHelper.getAadharNumber(getContext()),epss,pss).enqueue(new Callback<SendEkycResponse>() {
            @Override
            public void onResponse(Call<SendEkycResponse> call, Response<SendEkycResponse> response) {
                progressDialog.dismiss();
                switch (response.code()){
                    case 200:
                    case 502:
                        Log.d("Mohan","Ekyc Uploaded");
                        Log.d("Mohan",transactionId);
                        Log.d("Mohan",response.message());

                        //Update in Client Side DB
                        LenderTransactions curTransaction = TransactionDatabase.getInstance(getContext()).lenderTransactionsDao().getTransaction(transactionId);
                        Log.d("Mohan",curTransaction.getTransactionID());
                        Log.d("Mohan",curTransaction.getTransactionStatus());
                        curTransaction.setTransactionStatus("accepted");
                        TransactionDatabase.getInstance(getContext()).lenderTransactionsDao().insertTransaction(curTransaction);
                        Log.d("Mohan","FFF");

                        sendToLenderAddressApprovedAckPage();
                        break;

                    case 400:
                        Toast.makeText(getActivity(),"Invalid request parameters",Toast.LENGTH_SHORT).show();
                        break;

                    case 403:
                        Toast.makeText(getActivity(),"Invalid transactionID. Address request acceptance failed",Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        Toast.makeText(getActivity(),"Error code: "+String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                        break;
                }

            }
            @Override
            public void onFailure(Call<SendEkycResponse> call, Throwable t) {
                progressDialog.dismiss();
//                Toast.makeText(getContext(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(),"Unable to contact the server. Try again later",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendOTP(String captchaText, String captchaTxnId){
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Sending OTP...");
        progressDialog.show();
        OfflineEKYCService.makeOTPCall(SharedPrefHelper.getAadharNumber(getContext()),captchaTxnId,captchaText).enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                progressDialog.dismiss();
                if (response.body().getStatus().equals("Success")) {
                    otpTxnId = response.body().getTxnId();

                    Log.d("eKYC", response.body().getMessage());
                    Log.d("eKYC",otp_edit_text.getText().toString());
                    Log.d("eKYC",transactionId);
//                    Log.d("eKYC",SharedPrefHelper.getAadharNumber(getContext()));
                    Log.d("eKYC",otpTxnId);

                }
                else{
                    Log.d("eKYC","FFFF");
                    Log.d("eKYC",call.request().body().toString());
                    Toast.makeText(getActivity(),"Error code: "+response.message(),Toast.LENGTH_SHORT).show();
//                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                }

            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(),"Unable to contact the UIDAI server. Try again later",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                //End App
            }
        });
    }
}