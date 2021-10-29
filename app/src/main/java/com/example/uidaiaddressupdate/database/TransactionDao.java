package com.example.uidaiaddressupdate.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {

    @Query("SELECT * FROM `Transaction`")
    List<Transaction> getTransactionList();

    @Query("SELECT * FROM `Transaction` WHERE `Transaction`.transactionID = :transactionId")
    Transaction getTransaction(String transactionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTransaction(Transaction transaction);
}
