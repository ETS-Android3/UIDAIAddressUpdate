package com.example.uidaiaddressupdate.service.fcmservice;

import android.util.Log;

import com.example.uidaiaddressupdate.DecryptionUtils;
import com.example.uidaiaddressupdate.NewAddressRequestMessage;
import com.example.uidaiaddressupdate.database.LandlordTransactions;
import com.example.uidaiaddressupdate.database.LandlordTransactionsDao;
import com.example.uidaiaddressupdate.database.RenterTransactions;
import com.example.uidaiaddressupdate.database.RenterTransactionsDao;
import com.example.uidaiaddressupdate.database.TransactionDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private TransactionDatabase transactionDatabase;
    private LandlordTransactionsDao landlordTransactionsDao;
    private RenterTransactionsDao renterTransactionsDao;
    @Override
    public void onCreate() {
        super.onCreate();
        transactionDatabase = TransactionDatabase.getInstance(getApplicationContext());
        landlordTransactionsDao = transactionDatabase.landlordTransactionsDao();
        renterTransactionsDao = transactionDatabase.renterTransactionsDao();
        Log.d("FCMService", "Created");
    }

    @Override
    public void onNewToken(String token) {
        Log.d("FCMService", "Refreshed token: " + token);
        // TODO: Add action
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> messageData = remoteMessage.getData();
        String transactionStatus = getValueFromMap(messageData, "status");
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
                Log.d("FCMService", "Invalid Message Status "+transactionStatus);
        }
    }

    private String getValueFromMap(Map<String, String> m, Object key) {
        String value = m.get(key);
        return value == null? "" : value;
    }

    private void handleInitMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Init message received");
        String transactionID = getValueFromMap(messageData, "transactionID");
        String requesterSC = getValueFromMap(messageData, "requesterSC");
        String encryptedMessage = getValueFromMap(messageData, "encryptedMessage");
        Log.d("FCMService ",encryptedMessage);
        String decryptedMessage = null;
        try {
            decryptedMessage = DecryptionUtils.decryptMessage(encryptedMessage);
            Log.d("FCMService ",decryptedMessage);
            Gson gson = new Gson();
            NewAddressRequestMessage addressRequestMessage = gson.fromJson(decryptedMessage, NewAddressRequestMessage.class);
            Log.d("FCMService ",addressRequestMessage.getRenterName());
            Log.d("FCMService ",addressRequestMessage.getRenterNumber());
            landlordTransactionsDao.insertTransaction(new LandlordTransactions(transactionID, addressRequestMessage.getRenterName(), addressRequestMessage.getRenterNumber(), "init", "", requesterSC));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleAcceptedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Accepted message received");
        String transactionID = getValueFromMap(messageData, "transactionID");
        RenterTransactions renterTransactions = renterTransactionsDao.getTransaction(transactionID);
        renterTransactions.setTransactionStatus("accepted");
        renterTransactionsDao.insertTransaction(renterTransactions);
    }

    private void handleRejectedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Rejected message received");
        String transactionID = getValueFromMap(messageData, "transactionID");
        RenterTransactions renterTransactions = renterTransactionsDao.getTransaction(transactionID);
        renterTransactions.setTransactionStatus("rejected");
        renterTransactionsDao.insertTransaction(renterTransactions);
    }
    private void handleSharedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Shared message received");
    }
    private void handleAbortedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Aborted message received");
    }
    private void handleCommittedMessage(Map<String, String> messageData) {
        Log.d("FCMService", "Committed message received");
        String transactionID = getValueFromMap(messageData, "transactionID");
        String newAddress = getValueFromMap(messageData, "newAddress");
        LandlordTransactions landlordTransactions = landlordTransactionsDao.getTransaction(transactionID);
        landlordTransactions.setTransactionStatus("committed");
        landlordTransactions.setData(newAddress);
        landlordTransactionsDao.insertTransaction(landlordTransactions);
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
