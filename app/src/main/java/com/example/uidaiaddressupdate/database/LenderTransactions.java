package com.example.uidaiaddressupdate.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LenderTransactions {
    @NonNull
    @PrimaryKey
    public String transactionID;

    @ColumnInfo
    public String requesterName;

    @ColumnInfo
    public String requesterNumber;

    @ColumnInfo
    public String transactionStatus;

    @ColumnInfo
    public String data;

    @ColumnInfo
    public String shareCode;

    public LenderTransactions(){

    }
    public LenderTransactions(@NonNull String transactionID, String requesterName, String requesterNumber, String transactionStatus, String data, String shareCode) {
        this.transactionID = transactionID;
        this.requesterName = requesterName;
        this.requesterNumber = requesterNumber;
        this.transactionStatus = transactionStatus;
        this.data = data;
        this.shareCode = shareCode;
    }

    @NonNull
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(@NonNull String transactionID) {
        this.transactionID = transactionID;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequesterNumber() {
        return requesterNumber;
    }

    public void setRequesterNumber(String requesterNumber) {
        this.requesterNumber = requesterNumber;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }
}
