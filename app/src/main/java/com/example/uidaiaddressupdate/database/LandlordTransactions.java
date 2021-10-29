package com.example.uidaiaddressupdate.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LandlordTransactions {
    @NonNull
    @PrimaryKey
    public String transactionID;

    @ColumnInfo
    public String renterName;

    @ColumnInfo
    public String renterNumber;

    @ColumnInfo
    public String transactionStatus;

    @ColumnInfo
    public String data;

    @ColumnInfo
    public String shareCode;

    public LandlordTransactions(){

    }
    public LandlordTransactions(@NonNull String transactionID, String renterName, String renterNumber, String transactionStatus, String data, String shareCode) {
        this.transactionID = transactionID;
        this.renterName = renterName;
        this.renterNumber = renterNumber;
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

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getRenterNumber() {
        return renterNumber;
    }

    public void setRenterNumber(String renterNumber) {
        this.renterNumber = renterNumber;
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
