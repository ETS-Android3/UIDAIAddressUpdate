package com.example.uidaiaddressupdate.landlord;

public interface LandlordRequests {
    public void GotToCaptchaPage(String transactionId);
    public void HandleRequestDeclined(String transactionid);
}
