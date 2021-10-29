package com.example.uidaiaddressupdate.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Transaction {
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
}
