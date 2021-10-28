package com.example.uidaiaddressupdate.service.auth;

import com.example.uidaiaddressupdate.service.auth.model.Authotprequest;
import com.example.uidaiaddressupdate.service.auth.model.Authotpresponse;
import com.example.uidaiaddressupdate.service.auth.model.Authuidrequest;
import com.example.uidaiaddressupdate.service.auth.model.Authuidresponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthApiEndpointInterface {
    @Headers({"Content-Type: application/json"})
    @POST("/authuid")
    Call<Authuidresponse> sendOtp(@Body Authuidrequest authuidrequest);

    @Headers({"Content-Type: application/json"})
    @POST("/authotp")
    Call<Authotpresponse> authenticate(@Body Authotprequest authotprequest);

}
