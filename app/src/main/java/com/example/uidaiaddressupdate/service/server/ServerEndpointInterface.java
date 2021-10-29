package com.example.uidaiaddressupdate.service.server;

import com.example.uidaiaddressupdate.service.server.model.getpublickey.Publickeyrequest;
import com.example.uidaiaddressupdate.service.server.model.getpublickey.Publickeyresponse;
import com.example.uidaiaddressupdate.service.server.model.sendaddressrequest.Addressrequestformat;
import com.example.uidaiaddressupdate.service.server.model.sendaddressrequest.Addressrequestresponse;
import com.example.uidaiaddressupdate.service.server.model.sendekyc.Sendekycrequest;
import com.example.uidaiaddressupdate.service.server.model.sendekyc.Sendekycresponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServerEndpointInterface {
    @Headers({"Content-Type: application/json"})
    @POST("/postekyc")
    Call<Sendekycresponse> sendEkyc(@Body Sendekycrequest sendekycrequest);

    @Headers({"Content-Type: application/json"})
    @POST("/getpublickey")
    Call<Publickeyresponse> getPublicKey(@Body Publickeyrequest publickeyrequest);

    @Headers({"Content-Type: application/json"})
    @POST("/sendrequest")
    Call<Addressrequestresponse> sendrequest(@Body Addressrequestformat addressrequestformat);
}
