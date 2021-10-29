package com.example.uidaiaddressupdate.landlord;

public interface LandlordRequests {
    public void GotToCaptchaPage(String transactionId, String receiverShareCode);
    public void HandleRequestDeclined(String transactionid, String receiverShareCode);
}
