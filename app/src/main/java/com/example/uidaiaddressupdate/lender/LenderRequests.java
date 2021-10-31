package com.example.uidaiaddressupdate.lender;

public interface LenderRequests {
    public void GotToCaptchaPage(String transactionId, String receiverShareCode);
    public void HandleRequestDeclined(String transactionid, String receiverShareCode);
}
