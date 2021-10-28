package com.example.uidaiaddressupdate.service.auth.model;

import java.io.Serializable;

public class Authotpresponse implements Serializable {
    private String shareableCode;
    private String authToken;
    private String uidToken;

    public String getShareableCode() {
        return shareableCode;
    }

    public void setShareableCode(String shareableCode) {
        this.shareableCode = shareableCode;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUidToken() {
        return uidToken;
    }

    public void setUidToken(String uidToken) {
        this.uidToken = uidToken;
    }
}
