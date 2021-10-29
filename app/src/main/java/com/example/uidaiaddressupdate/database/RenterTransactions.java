package com.example.uidaiaddressupdate.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RenterTransactions {
    @NonNull
    @PrimaryKey
    public String transactionID;

    @ColumnInfo
    public String transactionStatus;

    @ColumnInfo
    public String data;
}
