package com.example.uidaiaddressupdate.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LandlordTransactionsDao {

    @Query("SELECT * FROM LandlordTransactions")
    List<LandlordTransactions> getTransactionList();

    @Query("SELECT * FROM LandlordTransactions WHERE LandlordTransactions.transactionID = :transactionId")
    LandlordTransactions getTransaction(String transactionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTransaction(LandlordTransactions landlordTransactions);

    @Query("DELETE FROM LandlordTransactions WHERE LandlordTransactions.transactionID = :transactionId")
    void deleteTransaction(String transactionId);
}
