package com.example.uidaiaddressupdate.service.auth;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.service.auth.AuthApiEndpointInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthapiService {
    private static AuthApiEndpointInterface apiEndpointInterface = null;
    private static Retrofit retrofit = null;

    public static AuthApiEndpointInterface getApiInstance() {
        if (apiEndpointInterface == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiEndpointInterface = retrofit.create(AuthApiEndpointInterface.class);
        }
        return apiEndpointInterface;
    }
}
