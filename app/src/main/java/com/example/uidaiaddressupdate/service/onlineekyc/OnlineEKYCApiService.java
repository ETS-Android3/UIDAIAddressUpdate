package com.example.uidaiaddressupdate.service.onlineekyc;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.service.server.ServerEndpointInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnlineEKYCApiService {
    private static OnlineEKYCapiEndpointInterface apiEndpointInterface = null;
    private static Retrofit retrofit = null;

    public static OnlineEKYCapiEndpointInterface getApiInstance() {
        if (apiEndpointInterface == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.UIDAI_SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiEndpointInterface = retrofit.create(OnlineEKYCapiEndpointInterface.class);
        }
        return apiEndpointInterface;
    }
}
