package com.example.uidaiaddressupdate.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RequesterTransactionsDao {

    @Query("SELECT * FROM RequesterTransactions")
    List<RequesterTransactions> getTransactionList();

    @Query("SELECT * FROM RequesterTransactions WHERE RequesterTransactions.transactionID = :transactionId")
    RequesterTransactions getTransaction(String transactionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTransaction(RequesterTransactions requesterTransactions);
}
