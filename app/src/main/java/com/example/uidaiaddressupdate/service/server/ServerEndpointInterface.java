package com.example.uidaiaddressupdate.service.server;

import com.example.uidaiaddressupdate.service.server.model.getekyc.GetEkycRequest;
import com.example.uidaiaddressupdate.service.server.model.getekyc.GetEkycResponse;
import com.example.uidaiaddressupdate.service.server.model.getpublickey.PublicKeyRequest;
import com.example.uidaiaddressupdate.service.server.model.getpublickey.PublicKeyResponse;
import com.example.uidaiaddressupdate.service.server.model.rejectrequest.RejectRequestFormat;
import com.example.uidaiaddressupdate.service.server.model.rejectrequest.RejectRequestResponse;
import com.example.uidaiaddressupdate.service.server.model.sendaddressrequest.AddressRequestFormat;
import com.example.uidaiaddressupdate.service.server.model.sendaddressrequest.AddressRequestResponse;
import com.example.uidaiaddressupdate.service.server.model.sendekyc.SendEkycRequest;
import com.example.uidaiaddressupdate.service.server.model.sendekyc.SendEkycResponse;
import com.example.uidaiaddressupdate.service.server.model.updateAddress.UpdateAddressRequest;
import com.example.uidaiaddressupdate.service.server.model.updateAddress.UpdateAddressResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServerEndpointInterface {
    @Headers({"Content-Type: application/json"})
    @POST("/postekyc")
    Call<SendEkycResponse> sendEkyc(@Body SendEkycRequest sendekycrequest);

    @Headers({"Content-Type: application/json"})
    @POST("/getpublickey")
    Call<PublicKeyResponse> getPublicKey(@Body PublicKeyRequest publickeyrequest);

    @Headers({"Content-Type: application/json"})
    @POST("/sendrequest")
    Call<AddressRequestResponse> sendRequest(@Body AddressRequestFormat addressrequestformat);

    @Headers({"Content-Type: application/json"})
    @POST("/getekyc")
    Call<GetEkycResponse> getEkyc(@Body GetEkycRequest getEkycRequest);

    @Headers({"Content-Type: application/json"})
    @POST("/updateaddress")
    Call<UpdateAddressResponse> updateAddress(@Body UpdateAddressRequest updateAddressRequest);

    @Headers({"Content-Type: application/json"})
    @POST("/rejectrequest")
    Call<RejectRequestResponse> rejectRequest(@Body RejectRequestFormat rejectRequestFormat);
}
