package com.example.uidaiaddressupdate.service.server.model.getpublickey;

import com.google.gson.annotations.SerializedName;

public class PublicKeyRequest {
    @SerializedName("uidToken")
    private String uidToken;

    @SerializedName("authToken")
    private String authToken;

    @SerializedName("shareableCode")
    private String shareableCode;

    public PublicKeyRequest(String uidToken, String authToken, String shareableCode) {
        this.uidToken = uidToken;
        this.authToken = authToken;
        this.shareableCode = shareableCode;
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

    public void setShareableCode(String shareableCode) {
        this.shareableCode = shareableCode;
    }

    public String getShareableCode() {
        return shareableCode;
    }
}
