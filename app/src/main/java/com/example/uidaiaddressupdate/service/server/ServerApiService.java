package com.example.uidaiaddressupdate.service.server;
import com.example.uidaiaddressupdate.service.server.model.getekyc.GetEkycRequest;
import com.example.uidaiaddressupdate.service.server.model.getekyc.GetEkycResponse;
import com.example.uidaiaddressupdate.service.server.model.getpublickey.Publickeyrequest;
import com.example.uidaiaddressupdate.service.server.model.getpublickey.Publickeyresponse;
import com.example.uidaiaddressupdate.service.server.model.sendekyc.Sendekycrequest;
import com.example.uidaiaddressupdate.service.server.model.sendekyc.Sendekycresponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServerApiService {
    private static ServerEndpointInterface apiEndpointInterface = null;
    private static Retrofit retrofit = null;
    public static ServerEndpointInterface getApiInstance(){
        if(apiEndpointInterface == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://52.147.193.119:8000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiEndpointInterface = retrofit.create(ServerEndpointInterface.class);
        }
        return apiEndpointInterface;
    }

    public static Call<GetEkycResponse> getEkyc(String transactionId){
        return getApiInstance().getEkyc(transactionId);
    }

    public static Call<Sendekycresponse> sendEkyc(String transactionId, String filename, String passcode,String ekyc){
        Sendekycrequest sendekycrequest  =  new Sendekycrequest(transactionId,ekyc,filename,passcode);
        return getApiInstance().sendEkyc(sendekycrequest);
    }
}
