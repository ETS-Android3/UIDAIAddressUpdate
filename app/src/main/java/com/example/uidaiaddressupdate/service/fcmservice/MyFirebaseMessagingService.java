package com.example.uidaiaddressupdate.service.fcmservice;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("FCMService", "Created");
    }

    @Override
    public void onNewToken(String token) {
        Log.d("FCMService", "Refreshed token: " + token);
        // TODO: Add action
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> messageData = remoteMessage.getData();
        String transactionStatus = messageData.get("transactionStatus");
        if(transactionStatus == null)
            transactionStatus = "";
        switch (transactionStatus) {
            case "init":
                handleInitMessage(messageData);
                break;
            case "accepted":
                handleAcceptedMessage(messageData);
                break;
            case "rejected":
                handleRejectedMessage(messageData);
                break;
            case "shared":
                handleSharedMessage(messageData);
                break;
            case "committed":
                handleCommittedMessage(messageData);
                break;
            case "aborted":
                handleAbortedMessage(messageData);
                break;
            default:
                Log.d("FCMService", "Invalid Message Status");
        }
    }

    private void handleInitMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Init message received");
    }

    private void handleAcceptedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Accepted message received");
    }

    private void handleRejectedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Rejected message received");
    }
    private void handleSharedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Shared message received");
    }
    private void handleAbortedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Aborted message received");
    }
    private void handleCommittedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Committed message received");
    }
}
// message
// id
// title
// body
// data
    // transactionID
    // transactionStatus
    // data
