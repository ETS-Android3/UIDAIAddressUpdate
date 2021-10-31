package com.example.uidaiaddressupdate.service.auth.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Authuidrequest {
    @SerializedName("uid")
    private String uid;
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
