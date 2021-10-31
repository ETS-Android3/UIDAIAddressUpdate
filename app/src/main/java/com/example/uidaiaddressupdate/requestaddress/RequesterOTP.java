package com.example.uidaiaddressupdate.requestaddress;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uidaiaddressupdate.EncryptionUtils;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.SharedPrefHelper;
import com.example.uidaiaddressupdate.XMLUtils;
import com.example.uidaiaddressupdate.database.RequesterTransactions;
import com.example.uidaiaddressupdate.database.TransactionDatabase;
import com.example.uidaiaddressupdate.service.onlineekyc.OnlineEKYCApiService;
import com.example.uidaiaddressupdate.service.onlineekyc.model.ekyconline.OnlineEKYCRequest;
import com.example.uidaiaddressupdate.service.onlineekyc.model.ekyconline.OnlineEKYCResponse;
import com.example.uidaiaddressupdate.service.onlineekyc.model.otp.OtpRequest;
import com.example.uidaiaddressupdate.service.onlineekyc.model.otp.OtpResponse;
import com.example.uidaiaddressupdate.service.server.ServerApiService;
import com.example.uidaiaddressupdate.service.server.model.sendaddressrequest.AddressRequestFormat;
import com.example.uidaiaddressupdate.service.server.model.sendaddressrequest.AddressRequestResponse;
import com.google.gson.Gson;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequesterOTP extends Fragment {

    private String receiverPublicKey;
    private String receiverSharableCode;
    private Button submit_otp;
    private String txnId;
    private EditText otp_edit_text;

    private View view;

    public RequesterOTP() {
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
        view = inflater.inflate(R.layout.fragment_requester_o_t_p, container, false);

        receiverPublicKey = getArguments().getString("publicKey");
        receiverSharableCode = getArguments().getString("recieverShareCode");
        Log.d("NEW_TESTING",receiverSharableCode);

        txnId = UUID.randomUUID().toString();


        otp_edit_text = (EditText) view.findViewById(R.id.requester_otp_et_enter_otp);
        submit_otp = (Button) view.findViewById(R.id.requester_otp_verify_button);

        OtpRequest otpRequest = new OtpRequest(SharedPrefHelper.getAadharNumber(getContext()),txnId);
        Log.d("NEW_TESTING",txnId);
        Log.d("NEW_TESTING",SharedPrefHelper.getAadharNumber(getContext()));
        OnlineEKYCApiService.getApiInstance().sendOtpOnPhone(otpRequest).enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {

                if (response.body().getStatus().equals("Y") || response.body().getStatus().equals("y")) {
                    Log.d("NEW_TESTING", response.message());
                    Log.d("NEW_TESTING", "success");
                }
                else{
                    // TODO: OTP not sent. Do something
                    Toast.makeText(getActivity(),"Error code: "+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Unable to contact the UIDAI server. Try again later",Toast.LENGTH_SHORT).show();
                Log.d("NEW_TESTING","error");
                t.printStackTrace();
            }
        });

        submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otp_edit_text.getText().toString().length() != 6){
                    otp_edit_text.setError("Enter Correct OTP");
                    otp_edit_text.requestFocus();
                    Toast.makeText(getContext(), "Enter Correct OTP", Toast.LENGTH_SHORT).show();
                    return;
                }
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Verifying OTP...");
                progressDialog.show();
                OnlineEKYCApiService.getApiInstance().getOnlineEKYC(new OnlineEKYCRequest(SharedPrefHelper.getAadharNumber(getContext()),txnId,otp_edit_text.getText().toString())).enqueue(new Callback<OnlineEKYCResponse>() {
                    @Override
                    public void onResponse(Call<OnlineEKYCResponse> call, Response<OnlineEKYCResponse> response) {
                        Log.d("KYC", response.body().getStatus());

                        if (response.body().getStatus().equals("Y") || response.body().getStatus().equals("y")){
                            String addressMessage;
                            String encryptedAddressMessage;
                            Log.d("KYC", response.message());
                            Log.d("KYC", response.body().geteKycString());
                            try {
                                addressMessage = new Gson().toJson(XMLUtils.createAddressRequestMessageFromKYC(response.body().geteKycString()));
                                Log.d("KYC", addressMessage);

                                encryptedAddressMessage = EncryptionUtils.encryptMessage(receiverPublicKey,addressMessage);

                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }

                            AddressRequestFormat addressrequestformat =
                                    new AddressRequestFormat(receiverSharableCode,
                                            SharedPrefHelper.getUidToken(getContext()),
                                            SharedPrefHelper.getAuthToken(getContext()),
                                            encryptedAddressMessage);

                            progressDialog.setMessage("Sending Address Request to Lender...");
                            ServerApiService.getApiInstance().sendRequest(addressrequestformat).enqueue(new Callback<AddressRequestResponse>() {
                                @Override
                                public void onResponse(Call<AddressRequestResponse> call, Response<AddressRequestResponse> response) {
                                    progressDialog.dismiss();
                                    switch (response.code()){
                                        case 200:
                                            Log.d("AddReq","SADADS");
                                            Log.d("AddReq",response.message());
//                                              Log.d("AddReq",response.body().getBody());
                                            Log.d("AddReq",response.body().getTransactionID());

                                            //Update in Client Side DB
                                            RequesterTransactions newTransaction = new RequesterTransactions(response.body().getTransactionID(),"init","",receiverSharableCode);
                                            TransactionDatabase.getInstance(getContext()).requesterTransactionsDao().insertTransaction(newTransaction);

                                            goToRequestSentPage();
                                            break;

                                        case 400:
                                            Toast.makeText(getActivity(),"Invalid request parameters",Toast.LENGTH_SHORT).show();
                                            break;

                                        case 502:
                                            Toast.makeText(getActivity(),"Unable to send request to lender. Aborting",Toast.LENGTH_SHORT).show();
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
                                public void onFailure(Call<AddressRequestResponse> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(),"Unable to contact the server. Try again later",Toast.LENGTH_SHORT).show();
                                    //Error
                                }
                            });
                        }
                        else {
//                            Log.d("KYC", response.body().getErrCode());
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(),"Invalid OTP",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<OnlineEKYCResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),"Unable to contact the UIDAI server. Try again later",Toast.LENGTH_SHORT).show();
                        //Error
                    }
                });
            }
        });
        return view;
    }

    private void goToRequestSentPage(){
        Navigation.findNavController(view).navigate(R.id.action_requesterOTP_to_requestSentPage);
    }
}