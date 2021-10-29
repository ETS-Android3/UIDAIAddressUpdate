package com.example.uidaiaddressupdate;

public class NewAddressRequestMessage {
    private String renterName;
    private String renterNumber;

    public NewAddressRequestMessage(String renterName, String renterNumber) {
        this.renterName = renterName;
        this.renterNumber = renterNumber;
    }

    public String getRenterName() {
        return renterName;
    }

    public String getRenterNumber() {
        return renterNumber;
    }

    public String getShareCode() {
        return shareCode;
    }

    @Override
    public String toString() {
        return "NewAddressRequestMessage{" +
                "renterName='" + renterName + '\'' +
                ", renterNumber='" + renterNumber + '\'' +
                '}';
    }
}
