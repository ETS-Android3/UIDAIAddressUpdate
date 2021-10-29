package com.example.uidaiaddressupdate.service.fcmservice;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        Log.d("MessagingService", "Refreshed token: " + token);
        // TODO: Add action
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        return;
    }

}
