package com.example.uidaiaddressupdate.service.server;
import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.service.server.model.getekyc.GetEkycRequest;
import com.example.uidaiaddressupdate.service.server.model.getekyc.GetEkycResponse;
import com.example.uidaiaddressupdate.service.server.model.sendekyc.SendEkycRequest;
import com.example.uidaiaddressupdate.service.server.model.sendekyc.SendEkycResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServerApiService {
    private static ServerEndpointInterface apiEndpointInterface = null;
    private static Retrofit retrofit = null;
    public static ServerEndpointInterface getApiInstance(){
        if(apiEndpointInterface == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiEndpointInterface = retrofit.create(ServerEndpointInterface.class);
        }
        return apiEndpointInterface;
    }

    public static Call<GetEkycResponse> getEkyc(GetEkycRequest getEkycRequest){
        return getApiInstance().getEkyc(getEkycRequest);
    }

    public static Call<SendEkycResponse> sendEkyc(String uidToken, String authToken, String transactionId, String filename, String passcode, String ekyc){
        SendEkycRequest sendekycrequest  =  new SendEkycRequest(uidToken,authToken,transactionId,ekyc,filename,passcode);
        return getApiInstance().sendEkyc(sendekycrequest);
    }
}
