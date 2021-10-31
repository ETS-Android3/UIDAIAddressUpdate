package com.example.uidaiaddressupdate.service.server.model.sendaddressrequest;

import com.google.gson.annotations.SerializedName;

public class AddressRequestFormat {
    @SerializedName("receiverSC")
    private String receiverSC;

    @SerializedName("uidToken")
    private String uidToken;

    @SerializedName("authToken")
    private String authToken;

    @SerializedName("message")
    private String message;

    public AddressRequestFormat(String receiverSC, String uidToken, String authToken, String message) {
        this.receiverSC = receiverSC;
        this.uidToken = uidToken;
        this.authToken = authToken;
        this.message = message;
    }

    public void setReceiverSC(String receiverSC) {
        this.receiverSC = receiverSC;
    }

    public String getReceiverSC() {
        return receiverSC;
    }

    public void setUidToken(String uidToken) {
        this.uidToken = uidToken;
    }

    public String getUidToken() {
        return uidToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    public String getAuthToken() {
        return authToken;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
