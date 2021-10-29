package com.example.uidaiaddressupdate.service.server;


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
}
