package com.example.uidaiaddressupdate.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RequesterTransactions {
    @NonNull
    @PrimaryKey
    public String transactionID;

    @ColumnInfo
    public String transactionStatus;

    @ColumnInfo
    public String data;

    @ColumnInfo
    public String shareCode;

    public RequesterTransactions(){

    }

    public RequesterTransactions(@NonNull String transactionID, String transactionStatus, String data, String shareCode) {
        this.transactionID = transactionID;
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
