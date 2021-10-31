package com.example.uidaiaddressupdate;

public class NewAddressRequestMessage {
    private String requesterName;
    private String requesterNumber;

    public NewAddressRequestMessage(String requesterName, String requesterNumber) {
        this.requesterName = requesterName;
        this.requesterNumber = requesterNumber;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getRequesterNumber() {
        return requesterNumber;
    }

    @Override
    public String toString() {
        return "NewAddressRequestMessage{" +
                "requesterName='" + requesterName + '\'' +
                ", requesterNumber='" + requesterNumber + '\'' +
                '}';
    }
}
