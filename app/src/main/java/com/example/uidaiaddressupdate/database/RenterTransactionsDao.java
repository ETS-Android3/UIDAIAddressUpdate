package com.example.uidaiaddressupdate.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RenterTransactionsDao {

    @Query("SELECT * FROM RenterTransactions")
    List<RenterTransactions> getTransactionList();

    @Query("SELECT * FROM RenterTransactions WHERE RenterTransactions.transactionID = :transactionId")
    RenterTransactions getTransaction(String transactionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTransaction(RenterTransactions renterTransactions);
}
