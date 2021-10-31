package com.example.uidaiaddressupdate.service.server.model.getpublickey;

import java.io.Serializable;

public class PublicKeyResponse implements Serializable {
    private String publicKey;

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
