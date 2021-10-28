package com.example.uidaiaddressupdate.auth.ui.main;

import com.example.uidaiaddressupdate.service.auth.AuthApiEndpointInterface;

import retrofit2.Retrofit;

public class AuthapiService {

    private static AuthApiEndpointInterface apiEndpointInterface = null;
    private static Retrofit retrofit = null;
    public static AuthApiEndpointInterface getApiInstance(){
        if(apiEndpointInterface == null){
            retrofit = new Retrofit.Builder()
                    .build();

            apiEndpointInterface = retrofit.create(AuthApiEndpointInterface.class);
        }
        return apiEndpointInterface;
    }
}
