package com.example.uidaiaddressupdate.auth.ui.main;

import com.example.uidaiaddressupdate.service.auth.AuthApiEndpointInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthapiService {

    private static AuthApiEndpointInterface apiEndpointInterface = null;
    private static Retrofit retrofit = null;
    public static AuthApiEndpointInterface getApiInstance(){
        if(apiEndpointInterface == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://52.147.193.119:8000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiEndpointInterface = retrofit.create(AuthApiEndpointInterface.class);
        }
        return apiEndpointInterface;
    }
}
