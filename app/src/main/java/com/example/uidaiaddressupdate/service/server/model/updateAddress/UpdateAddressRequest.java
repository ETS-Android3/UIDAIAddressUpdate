package com.example.uidaiaddressupdate.service.server.model.updateAddress;

import com.example.uidaiaddressupdate.requestaddress.AddressModel;
import com.google.gson.annotations.SerializedName;

public class UpdateAddressRequest {
    @SerializedName("uidToken")
    private String uidToken;

    @SerializedName("authToken")
    private String authToken;

    @SerializedName("transactionID")
    private String transactionID;

    @SerializedName("oldAddress")
    private AddressModel oldAddress;

    @SerializedName("newAddress")
    private AddressModel newAddress;

    @SerializedName("gpsCoord")
    private float[] gpsCoord;

    @SerializedName("uid")
    private String uid;

    public UpdateAddressRequest(String uidToken, String authToken, String transactionID, AddressModel oldAddress, AddressModel newAddress, float[] gpsCoord, String uid) {
        this.uidToken = uidToken;
        this.authToken = authToken;
        this.transactionID = transactionID;
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
        this.gpsCoord = gpsCoord;
        this.uid = uid;
    }
}
