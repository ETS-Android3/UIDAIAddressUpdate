package com.example.uidaiaddressupdate.requestaddress;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.uidaiaddressupdate.EncryptionUtils;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.SharedPrefHelper;
import com.example.uidaiaddressupdate.XMLUtils;
import com.example.uidaiaddressupdate.service.onlineekyc.OnlineEKYCApiService;
import com.example.uidaiaddressupdate.service.onlineekyc.model.ekyconline.OnlineEKYCRequest;
import com.example.uidaiaddressupdate.service.onlineekyc.model.ekyconline.OnlineEKYCResponse;
import com.example.uidaiaddressupdate.service.onlineekyc.model.otp.OtpRequest;
import com.example.uidaiaddressupdate.service.onlineekyc.model.otp.OtpResponse;
import com.example.uidaiaddressupdate.service.server.ServerApiService;
import com.example.uidaiaddressupdate.service.server.model.sendaddressrequest.Addressrequestformat;
import com.example.uidaiaddressupdate.service.server.model.sendaddressrequest.Addressrequestresponse;
import com.google.gson.Gson;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RenterOTP extends Fragment {

    private String receiverPublicKey;
    private String receiverSharableCode;
    private Button submit_otp;
    private String txnId;
    private EditText otp_edit_text;

    private View view;

    public RenterOTP() {
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
        view = inflater.inflate(R.layout.fragment_renter_o_t_p, container, false);

        receiverPublicKey = getArguments().getString("publicKey");
        receiverSharableCode = getArguments().getString("recieverShareCode");

        txnId = UUID.randomUUID().toString();


        otp_edit_text = (EditText) view.findViewById(R.id.renter_otp_et_enter_otp);
        submit_otp = (Button) view.findViewById(R.id.renter_otp_verify_button);

        OtpRequest otpRequest = new OtpRequest("999952733847",txnId);
        OnlineEKYCApiService.getApiInstance().sendOtpOnPhone(otpRequest).enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                Log.d("NEW_TESTING",response.message());
            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {

            }
        });

        submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnlineEKYCApiService.getApiInstance().getOnlineEKYC(new OnlineEKYCRequest("999952733847",txnId,otp_edit_text.getText().toString())).enqueue(new Callback<OnlineEKYCResponse>() {
                    @Override
                    public void onResponse(Call<OnlineEKYCResponse> call, Response<OnlineEKYCResponse> response) {
                        String addressMessage;
                        String encryptedAddressMessage;

                        Log.d("KYC", response.body().geteKycString());
                        try {
                            addressMessage = new Gson().toJson(XMLUtils.createAddressRequestMessageFromKYC(response.body().geteKycString(),receiverSharableCode));
                            Log.d("KYC", addressMessage);

                            encryptedAddressMessage = EncryptionUtils.encryptMessage(receiverPublicKey,addressMessage);

                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }

                        Addressrequestformat addressrequestformat =
                                new Addressrequestformat(receiverSharableCode,
                                        "999952733847",
                                        SharedPrefHelper.getAuthToken(getContext()),
                                        encryptedAddressMessage);

                        ServerApiService.getApiInstance().sendrequest(addressrequestformat).enqueue(new Callback<Addressrequestresponse>() {
                            @Override
                            public void onResponse(Call<Addressrequestresponse> call, Response<Addressrequestresponse> response) {
                                Log.d("AddReq","SADADS");
                                Log.d("AddReq",response.message());
//                                Log.d("AddReq",response.body().getBody());
//                                Log.d("AddReq",response.body().getTransactionNo());

                                // TODO: Add Newly Created Transaction in RenterTransactions Table in Database
                                goToRequestSentPage();
                            }

                            @Override
                            public void onFailure(Call<Addressrequestresponse> call, Throwable t) {
                                //Error
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<OnlineEKYCResponse> call, Throwable t) {
                        //Error
                    }
                });
            }
        });
        return view;
    }

    private void goToRequestSentPage(){
        Navigation.findNavController(view).navigate(R.id.action_renterOTP_to_requestSentPage);
    }
}