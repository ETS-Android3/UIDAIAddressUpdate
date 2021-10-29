package com.example.uidaiaddressupdate;

public class NewAddressRequestMessage {
    private String renterName;
    private String renterNumber;
    private String shareCode;

    public NewAddressRequestMessage(String renterName, String renterNumber, String shareCode) {
        this.renterName = renterName;
        this.renterNumber = renterNumber;
        this.shareCode = shareCode;
    }

    @Override
    public String toString() {
        return "NewAddressRequestMessage{" +
                "renterName='" + renterName + '\'' +
                ", renterNumber='" + renterNumber + '\'' +
                ", shareCode='" + shareCode + '\'' +
                '}';
    }
}
