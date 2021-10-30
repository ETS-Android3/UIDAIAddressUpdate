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
import android.widget.Toast;

import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.SharedPrefHelper;
import com.example.uidaiaddressupdate.service.server.ServerApiService;
import com.example.uidaiaddressupdate.service.server.ServerEndpointInterface;
import com.example.uidaiaddressupdate.service.server.model.getpublickey.Publickeyrequest;
import com.example.uidaiaddressupdate.service.server.model.getpublickey.Publickeyresponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterShareCode extends Fragment {

    private Button share_code_submit;
    private EditText sharecode_edit_text;
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
        sharecode_edit_text = (EditText)view.findViewById(R.id.et_share_code);
        share_code_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recieverShareCode = sharecode_edit_text.getText().toString();
                Log.d("KYC",recieverShareCode);
                ServerApiService.getApiInstance().getPublicKey(new Publickeyrequest(SharedPrefHelper.getUidToken(getContext()),SharedPrefHelper.getAuthToken(getContext()),recieverShareCode)).enqueue(new Callback<Publickeyresponse>() {
                    @Override
                    public void onResponse(Call<Publickeyresponse> call, Response<Publickeyresponse> response) {
                        switch (response.code()){
                            case 200:
                                Log.d("KYC",response.message());
                                String publicKey = response.body().getPublicKey();
                                Bundle bundle = new Bundle();
                                bundle.putString("publicKey",publicKey);
                                bundle.putString("recieverShareCode",recieverShareCode);
                                Navigation.findNavController(view).navigate(R.id.action_enterShareCode_to_renterOTP,bundle);
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
                    public void onFailure(Call<Publickeyresponse> call, Throwable t) {
                        t.printStackTrace();
                        //Error
                    }
                });

            }
        });
        return view;
    }
}